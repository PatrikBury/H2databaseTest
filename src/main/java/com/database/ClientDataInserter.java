package com.database;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.math.BigDecimal;


public class ClientDataInserter {
    public static void insertClients(Connection connection) throws SQLException {
        // Naplnění tabulky Klient
        String insertKlientSQL = "INSERT INTO Klient (ID_klienta, Jméno, Příjmení, Datum_narození, Adresa) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertKlientSQL)) {
            for (int i = 1; i <= 50; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.setString(2, "Jméno" + i);
                preparedStatement.setString(3, "Příjmení" + i);
                preparedStatement.setDate(4, Date.valueOf("2000-01-01"));
                preparedStatement.setString(5, "Adresa" + i);
                preparedStatement.executeUpdate();
            }
        }

        // Naplnění tabulky Účet
        String insertUcetSQL = "INSERT INTO Účet (ID_účtu, ID_klienta, Číslo_účtu, Typ_účtu, Datum_vytvoření) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertUcetSQL)) {
            for (int i = 1; i <= 50; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.setInt(2, i);
                preparedStatement.setString(3, "Číslo_účtu" + i);
                preparedStatement.setString(4, "Běžný");
                preparedStatement.setDate(5, Date.valueOf("2025-01-01"));
                preparedStatement.executeUpdate();
            }
        }

        // Naplnění tabulky Transakce
        String insertTransakceSQL = "INSERT INTO Transakce (ID_transakce, ID_účtu, Částka, Datum_transakce, Typ_transakce) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertTransakceSQL)) {
            for (int i = 1; i <= 50; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.setInt(2, i);
                preparedStatement.setBigDecimal(3, new BigDecimal("1000.00"));
                preparedStatement.setDate(4, Date.valueOf("2025-02-01"));
                preparedStatement.setInt(5, 1); // Předpokládáme, že 1 je "Vklad" v Číselníku_typů_transakcí
                preparedStatement.executeUpdate();
            }
        }

        // Naplnění tabulky Balance
        String insertBalanceSQL = "INSERT INTO Balance (ID_balance, ID_účtu, Datum, Jistina, Úrok, Poplatky) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertBalanceSQL)) {
            for (int i = 1; i <= 50; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.setInt(2, i);
                preparedStatement.setDate(3, Date.valueOf("2025-02-01"));
                preparedStatement.setBigDecimal(4, new BigDecimal("5000.00"));
                preparedStatement.setBigDecimal(5, new BigDecimal("50.00"));
                preparedStatement.setBigDecimal(6, new BigDecimal("5.00"));
                preparedStatement.executeUpdate();
            }
        }

        System.out.println("Tabulky byly úspěšně naplněny daty pro 50 klientů.");
    }
}
