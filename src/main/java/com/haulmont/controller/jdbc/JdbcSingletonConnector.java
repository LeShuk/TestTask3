package com.haulmont.controller.jdbc;

import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.cmdline.SqlToolError;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

/**
 * The type Jdbc singleton connector.
 */
public class JdbcSingletonConnector {

    private static JdbcSingletonConnector instance = null;
    private Connection connection;

    private static final String CREATE_TABLES_SQL_FILEPATH = "sql/CreateTables.sql";
    private static final String CREATE_DEMO_DATA_SQL_FILEPATH = "sql/DemoData.sql";

    private JdbcSingletonConnector() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:mem:tt3", "SA", "");
            initDefaultDb();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static JdbcSingletonConnector getInstance() {
        if (instance == null) {
            instance = new JdbcSingletonConnector();
        }
        return instance;
    }

    /**
     * Gets connection.
     *
     * @return the connection
     */
    public Connection getConnection() {
        return connection;
    }

    private void initDefaultDb() {
        executeSqlScript(CREATE_TABLES_SQL_FILEPATH);
        executeSqlScript(CREATE_DEMO_DATA_SQL_FILEPATH);
    }

    private void executeSqlScript(String sqlScriptPath) {
        try {
            SqlFile sqlFile = new SqlFile(
                    new File(
                            Objects.requireNonNull(getClass().getClassLoader()
                                    .getResource(sqlScriptPath)).getFile()
                    )
            );

            sqlFile.setConnection(connection);
            sqlFile.execute();
        } catch (IOException | SqlToolError | SQLException e) {
            e.printStackTrace();
        }
    }
}
