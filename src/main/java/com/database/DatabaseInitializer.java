package com.database;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseInitializer {
    public static void initDatabase() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Vytvoření tabulky Klient
            stmt.execute("CREATE TABLE IF NOT EXISTS Klient (" +
                    "ID_klienta INT PRIMARY KEY," +
                    "Jméno VARCHAR(255)," +
                    "Příjmení VARCHAR(255)," +
                    "Datum_narození DATE," +
                    "Adresa VARCHAR(255))");

            // Vytvoření tabulky Účet
            stmt.execute("CREATE TABLE IF NOT EXISTS Účet (" +
                    "ID_účtu INT PRIMARY KEY," +
                    "ID_klienta INT," +
                    "Číslo_účtu VARCHAR(255)," +
                    "Typ_účtu VARCHAR(255)," +
                    "Datum_vytvoření DATE," +
                    "FOREIGN KEY (ID_klienta) REFERENCES Klient(ID_klienta))");

            // Vytvoření tabulky Transakce
            stmt.execute("CREATE TABLE IF NOT EXISTS Transakce (" +
                    "ID_transakce INT PRIMARY KEY," +
                    "ID_účtu INT," +
                    "Částka DECIMAL," +
                    "Datum_transakce DATE," +
                    "Typ_transakce INT," +
                    "FOREIGN KEY (ID_účtu) REFERENCES Účet(ID_účtu))");

            // Vytvoření tabulky Balance
            stmt.execute("CREATE TABLE IF NOT EXISTS Balance (" +
                    "ID_balance INT PRIMARY KEY," +
                    "ID_účtu INT," +
                    "Datum DATE," +
                    "Jistina DECIMAL," +
                    "Úrok DECIMAL," +
                    "Poplatky DECIMAL," +
                    "FOREIGN KEY (ID_účtu) REFERENCES Účet(ID_účtu))");

            // Vytvoření tabulky Číselník_typů_transakcí
            stmt.execute("CREATE TABLE IF NOT EXISTS Číselník_typů_transakcí (" +
                    "ID_typu_transakce INT PRIMARY KEY," +
                    "Popis_typu VARCHAR(255))");



            System.out.println("Tabulky byly úspěšně vytvořeny.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

