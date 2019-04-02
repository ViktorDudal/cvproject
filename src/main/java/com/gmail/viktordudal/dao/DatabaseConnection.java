package com.gmail.viktordudal.dao;

import java.sql.*;

public class DatabaseConnection {

    private static Connection connection;
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/cv_database_vdudal";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    private DatabaseConnection() {
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            initConnection();
        }
        return connection;
    }

    private static void initConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }

    }
}
