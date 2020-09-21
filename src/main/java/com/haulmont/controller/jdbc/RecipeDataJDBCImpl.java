package com.haulmont.controller.jdbc;

import com.haulmont.controller.dao.RecipeDao;
import com.haulmont.model.Doctor;
import com.haulmont.model.Patient;
import com.haulmont.model.Priority;
import com.haulmont.model.Recipe;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The type Recipe data jdbc.
 */
public class RecipeDataJDBCImpl implements RecipeDao {
    private JdbcSingletonConnector jdbcSingletonConnector;
    //language=sql
    private static final String SQL_SELECT_ALL_RECIPES =
            "SELECT RECIPES.ID, DESCRIPTION, CREATIONDATE, VALIDITYINDAYS, PRIORITY,\n" +
                    "D.ID AS D_ID, D.NAME AS D_NAME, D.PATRONYMIC AS D_PATRONYMIC, D.SURNAME AS D_SURNAME, SPECIALIZATION,\n" +
                    "P.ID AS P_ID, P.NAME AS P_NAME, P.PATRONYMIC AS P_PATRONYMIC, P.SURNAME AS P_SURNAME, PHONE\n" +
                    "FROM RECIPES\n" +
                    "INNER JOIN DOCTORS D on D.ID = RECIPES.DOCTORID\n" +
                    "INNER JOIN PATIENTS P on P.ID = RECIPES.PATIENTID";
    //language=sql
    private final String SQL_CREATE_NEW_RECIPE =
            "INSERT INTO RECIPES(patientid, doctorid, description, creationdate, validityindays, priority)" +
                    " VALUES(?, ?, ?, ?, ?, ?);";
    //language=sql
    private final String SQL_UPDATE_RECIPE =
            "UPDATE RECIPES SET PATIENTID = ?, DOCTORID = ?, DESCRIPTION = ?, " +
                    "CREATIONDATE = ?, VALIDITYINDAYS = ?, PRIORITY = ?" +
                    " WHERE ID = ?;";
    //language=sql
    private final String SQL_DELETE_RECIPE =
            "DELETE FROM RECIPES WHERE ID = ?;";

    /**
     * Instantiates a new Recipe data jdbc.
     */
    public RecipeDataJDBCImpl() {
        jdbcSingletonConnector = JdbcSingletonConnector.getInstance();
    }

    @Override
    public boolean create(Recipe model) {
        Connection connection = jdbcSingletonConnector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_NEW_RECIPE);
            preparedStatement.setLong(1, model.getPatient().getId());
            preparedStatement.setLong(2, model.getDoctor().getId());
            preparedStatement.setString(3, model.getDescription());
            preparedStatement.setDate(4, Date.valueOf(model.getCreationDate()));
            preparedStatement.setInt(5, model.getValidityInDays());
            preparedStatement.setInt(6, model.getPriority().ordinal());

            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Recipe model) {
        Connection connection = jdbcSingletonConnector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_RECIPE);
            preparedStatement.setLong(1, model.getPatient().getId());
            preparedStatement.setLong(2, model.getDoctor().getId());
            preparedStatement.setString(3, model.getDescription());
            preparedStatement.setDate(4, Date.valueOf(model.getCreationDate()));
            preparedStatement.setInt(5, model.getValidityInDays());
            preparedStatement.setInt(6, model.getPriority().ordinal());
            preparedStatement.setLong(7, model.getId());

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
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_RECIPE);
            preparedStatement.setLong(1, id);

            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Recipe findByID(Long id) {
        return null;
    }

    @Override
    public List<Recipe> findAll() {
        List<Recipe> recipeList = new ArrayList<>();

        try {
            Connection connection = jdbcSingletonConnector.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_RECIPES);

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String description = resultSet.getString("description");
                LocalDate creationDate = resultSet.getDate("creationdate").toLocalDate();
                Integer validityInDays = resultSet.getInt("validityindays");
                Priority priority = Priority.getPriorityByID(resultSet.getInt("priority"));

                Long doctorID = resultSet.getLong("D_ID");
                String doctorName = resultSet.getString("D_NAME");
                String doctorPatronymic = resultSet.getString("D_PATRONYMIC");
                String doctorSurname = resultSet.getString("D_SURNAME");
                String specialisation = resultSet.getString("SPECIALIZATION");
                Doctor doctor = new Doctor(doctorID, doctorName, doctorPatronymic,
                        doctorSurname, specialisation);

                Long patientID = resultSet.getLong("P_ID");
                String patientName = resultSet.getString("P_NAME");
                String patientPatronymic = resultSet.getString("P_PATRONYMIC");
                String patientSurname = resultSet.getString("P_SURNAME");
                String phone = resultSet.getString("PHONE");
                Patient patient = new Patient(patientID, patientName, patientPatronymic, patientSurname, phone);

                recipeList.add(new Recipe(id, patient, doctor,
                        description, creationDate, validityInDays, priority));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return recipeList;
    }

    @Override
    public List<Recipe> findAll(String description, Patient patient, Priority priority) {
        List<Recipe> recipeList = findAll();

        if ((description != null) && (!description.equals(""))) {
            Iterator<Recipe> recipeIterator = recipeList.iterator();
            while (recipeIterator.hasNext()) {
                Recipe nextRecipe = recipeIterator.next();
                if (!nextRecipe.getDescription().contains(description)) {
                    recipeIterator.remove();
                }
            }
        }

        if (patient != null) {
            Iterator<Recipe> recipeIterator = recipeList.iterator();
            while (recipeIterator.hasNext()) {
                Recipe nextRecipe = recipeIterator.next();
                if (nextRecipe.getPatient().getId() != patient.getId()) {
                    recipeIterator.remove();
                }
            }
        }

        if (priority != null) {
            Iterator<Recipe> recipeIterator = recipeList.iterator();
            while (recipeIterator.hasNext()) {
                Recipe nextRecipe = recipeIterator.next();
                if (!nextRecipe.getPriority().equals(priority)) {
                    recipeIterator.remove();
                }
            }
        }

        if (recipeList.isEmpty()) recipeList.add(Recipe.NULL_RECIPE);

        return recipeList;
    }
}
