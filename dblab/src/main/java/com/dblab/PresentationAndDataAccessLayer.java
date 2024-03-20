package com.dblab;
import java.sql.*;
import java.util.Scanner;

public class PresentationAndDataAccessLayer {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Connection connection = DataMgr.getArcadeGamesConnection();

            System.out.println("Choose an option:");
            System.out.println("1. Insert data into the database");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    insertData(connection);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertData(Connection connection) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter data to insert:");
            String inputData = scanner.nextLine();

            String sql = "INSERT INTO TableName (ColumnName) VALUES (?)";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, inputData);
                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Data inserted successfully!");
                } else {
                    System.out.println("Failed to insert data.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
