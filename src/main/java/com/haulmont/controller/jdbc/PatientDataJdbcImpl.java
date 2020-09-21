package com.haulmont.controller.jdbc;

import com.haulmont.controller.dao.PatientDao;
import com.haulmont.model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Patient data jdbc.
 */
public class PatientDataJdbcImpl implements PatientDao {
    private JdbcSingletonConnector jdbcSingletonConnector;

    //language=sql
    private final String SQL_CREATE_NEW_PATIENT =
            "INSERT INTO PATIENTS(name, patronymic, surname, phone) VALUES(?, ?, ?, ?);";
    //language=sql
    private final String SQL_UPDATE_PATIENT =
            "UPDATE PATIENTS SET NAME = ?, PATRONYMIC = ?, SURNAME = ?, PHONE = ? WHERE ID = ?;";
    //language=sql
    private final String SQL_DELETE_PATIENT =
            "DELETE FROM PATIENTS WHERE ID = ?;";
    //language=sql
    private final String SQL_SELECT_ALL = "SELECT * FROM PATIENTS;";

    /**
     * Instantiates a new Patient data jdbc.
     */
    public PatientDataJdbcImpl() {
        jdbcSingletonConnector = JdbcSingletonConnector.getInstance();
    }

    @Override
    public boolean create(Patient model) {
        Connection connection = jdbcSingletonConnector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_NEW_PATIENT);
            preparedStatement.setString(1, model.getName());
            preparedStatement.setString(2, model.getPatronymic());
            preparedStatement.setString(3, model.getSurname());
            preparedStatement.setString(4, model.getPhone());

            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Patient model) {
        Connection connection = jdbcSingletonConnector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PATIENT);
            preparedStatement.setString(1, model.getName());
            preparedStatement.setString(2, model.getPatronymic());
            preparedStatement.setString(3, model.getSurname());
            preparedStatement.setString(4, model.getPhone());
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
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_PATIENT);
            preparedStatement.setLong(1, id);

            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Patient findByID(Long id) {
        return null;
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> patientList = new ArrayList<>();
        try {
            Connection connection = jdbcSingletonConnector.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);

            while (resultSet.next()) {
                Long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String patronymic = resultSet.getString(3);
                String surname = resultSet.getString(4);
                String phone = resultSet.getString(5);

                Patient patient = new Patient(id, name, patronymic, surname, phone);
                patientList.add(patient);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return patientList;
    }
}
