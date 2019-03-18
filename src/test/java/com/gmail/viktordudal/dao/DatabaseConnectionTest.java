package com.gmail.viktordudal.dao;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectionTest {

    private static Connection connection = null;
    private static Statement statement;

    private static final String URL = "jdbc:postgresql://localhost:5432/users";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    @BeforeMethod
    public void setUp() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
            } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            }
    }

    @AfterMethod
    public void tearDown() {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
    }
}