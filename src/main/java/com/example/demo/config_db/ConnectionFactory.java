package com.example.demo.config_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = String.format("jdbc:postgresql://db:%d/%s", Integer.parseInt(System.getenv("PG_PORT")), System.getenv("PG_DATABASE"));
    private static final String USER = System.getenv("PG_USER");
    private static final String PASSWORD = System.getenv("PG_PASSWORD");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}