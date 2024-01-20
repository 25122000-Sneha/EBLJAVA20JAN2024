package com.wecp.progressive.config;
import java.sql.*;
public class DatabaseConnectionManager {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/healthcare_wecp";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "hellowecp";

    public static Connection getConnection() throws SQLException {
        // create a connection to database
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
}
