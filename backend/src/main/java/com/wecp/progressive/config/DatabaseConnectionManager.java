package com.wecp.progressive.config;
/*import java.sql.*;
public class DatabaseConnectionManager {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/healthcare_wecp";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "hellowecp";

    public static Connection getConnection() throws SQLException {
        // create a connection to database
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }*/
import java.sql.*;
import java.io.*;
import java.util.*;
public class DatabaseConnectionManager {
    private static final Properties properties = new Properties();

    static{
        loadProperties();
    }

    private static void loadProperties(){
        try (InputStream input = DatabaseConnectionManager.class.getClassLoader().getResourceAsStream("application.properties")) {
            if(input == null){
                throw new IllegalStateException("resource.properties not found in classpath");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading properties file", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = properties.getProperty("spring.datasource.url");
        String user = properties.getProperty("spring.datasource.username");
        String password = properties.getProperty("spring.datasource.password");

        return DriverManager.getConnection(url, user, password);
    }
}

