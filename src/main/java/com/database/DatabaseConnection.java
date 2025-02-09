package com.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


    public class DatabaseConnection {
        // Změníme URL na in-memory databázi pro jednodušší testování
        private static final String URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
        private static final String USER = "sa";
        private static final String PASSWORD = "";

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }
