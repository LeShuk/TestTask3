package com.haulmont.controller.jdbc;

import com.haulmont.controller.dao.DoctorDao;
import com.haulmont.model.Doctor;
import com.haulmont.model.reports.WrittenRecipes;

import javax.management.Query;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;

/**
 * The type Doctor data jdbc.
 */
public class DoctorDataJdbcImpl implements DoctorDao {
    private JdbcSingletonConnector jdbcSingletonConnector;

    //language=sql
    private final String SQL_CREATE_NEW_DOCTOR =
            "INSERT INTO DOCTORS(name, patronymic, surname, specialization) VALUES(?, ?, ?, ?);";
    //language=sql
    private final String SQL_UPDATE_DOCTOR =
            "UPDATE DOCTORS SET NAME = ?, PATRONYMIC = ?, SURNAME = ?, SPECIALIZATION = ? WHERE ID = ?;";
    //language=sql
    private final String SQL_DELETE_DOCTOR =
            "DELETE FROM DOCTORS WHERE ID = ?;";
    //language=sql
    private final String SQL_SELECT_ALL = "SELECT * FROM DOCTORS;";

    //language=sql
    private final String SQL_COUNT_WRITTEN_RECIPES =
            "SELECT DOCTORID, COUNT(*) AS writedReceips FROM RECIPES\n" +
                    "group by DOCTORID";

    /**
     * Instantiates a new Doctor data jdbc.
     */
    public DoctorDataJdbcImpl() {
        jdbcSingletonConnector = JdbcSingletonConnector.getInstance();
    }

    @Override
    public boolean create(Doctor model) {
        Connection connection = jdbcSingletonConnector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_NEW_DOCTOR);
            preparedStatement.setString(1, model.getName());
            preparedStatement.setString(2, model.getPatronymic());
            preparedStatement.setString(3, model.getSurname());
            preparedStatement.setString(4, model.getSpecialization());

            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Doctor model) {
        Connection connection = jdbcSingletonConnector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_DOCTOR);
            preparedStatement.setString(1, model.getName());
            preparedStatement.setString(2, model.getPatronymic());
            preparedStatement.setString(3, model.getSurname());
            preparedStatement.setString(4, model.getSpecialization());
            preparedStatement.setLong(5, model.getId());

            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteByID(Long id) {
        Connection connection = jdbcSingletonConnector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_DOCTOR);
            preparedStatement.setLong(1, id);

            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Doctor findByID(Long id) {
        return null;
    }

    @Override
    public List<Doctor> findAll() {
        List<Doctor> doctorList = new ArrayList<>();
        try {
            Connection connection = jdbcSingletonConnector.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String patronymic = resultSet.getString("patronymic");
                String surname = resultSet.getString("surname");
                String specialization = resultSet.getString("specialization");

                Doctor doctor = new Doctor(id, name, patronymic, surname, specialization);
                doctorList.add(doctor);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return doctorList;
    }

    @Override
    public List<WrittenRecipes> numberWrittenRecipes() {
        List<Doctor> doctorList = findAll();
        List<WrittenRecipes> numberWrittenRecipes = new ArrayList<>();

        for (Doctor doctor : doctorList)
            numberWrittenRecipes.add(new WrittenRecipes(doctor.getId(),
                    doctor.getSurnameNP() + ", " + doctor.getSpecialization(),
                    0));

        try {
            Connection connection = jdbcSingletonConnector.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_COUNT_WRITTEN_RECIPES);

            while (resultSet.next()) {
                for (WrittenRecipes writtenRecipes : numberWrittenRecipes) {
                    if (writtenRecipes.getDoctorId() == resultSet.getLong("DOCTORID")) {
                        writtenRecipes.setNumberRecipes(resultSet.getInt("WRITEDRECEIPS"));
                        break;
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return numberWrittenRecipes;
    }
}
