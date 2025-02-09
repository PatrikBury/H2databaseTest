package com.database;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;


public class Main {
    public static void main(String[] args) {
        System.out.println("Spouštím aplikaci...");

        DatabaseInitializer.initDatabase();

        // Naplnění tabulek daty o klientech
        try (Connection connection = DatabaseConnection.getConnection()) {
            ClientDataInserter.insertClients(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Čtení dat
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();

             ResultSet rs = stmt.executeQuery("SELECT * FROM klient")) {

            System.out.println("\nNalezení uživatelé:");
            System.out.println("-------------------");

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("ID_klienta") +
                        ", Jméno: " + rs.getString("Jméno") +
                        ", Příjmení: " + rs.getString("Příjmení") +
                        ", Datum narození: " + rs.getDate("Datum_narození") +
                        ", Adresa: " + rs.getString("Adresa"));
            }

        } catch (SQLException e) {
            System.out.println("Chyba při čtení dat: " + e.getMessage());
            e.printStackTrace();
        }


    }
}