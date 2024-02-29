package com.example.enterdataservice.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/data_db";

    private static final String USERNAME = "user";
    private static final String PASSWORD = "pass";

    public static String getJdbcUrl() {
        return JDBC_URL;
    }

    public static String getUsername() {
        return USERNAME;
    }

    public static String getPassword() {
        return PASSWORD;
    }

    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
}
