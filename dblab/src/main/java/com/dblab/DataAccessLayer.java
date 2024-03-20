package com.dblab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DataAccessLayer {
    public static ResultSet executeStatement(Connection connection, String query) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        return statement.executeQuery();
    }

    public static ResultSet executePreparedStatement(Connection connection, String query, String parameter) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, parameter);
        return preparedStatement.executeQuery();
    }

    public static ResultSet executeCallableStatement(Connection connection, String procedureName) throws SQLException {
        return connection.prepareCall("{call " + procedureName + "}").executeQuery();
    }

    public static Connection getMealPlanningConnection() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username for the MealPlanning database: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        return DriverManager.getConnection("jdbc:mysql://localhost/meal_planning", username, password);
    }

    public static Connection getArcadeGamesConnection() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username for the ArcadeGames database: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        return DriverManager.getConnection("jdbc:mysql://localhost/arcade_games", username, password);
    }

    public static Connection getVideoGameSystemsConnection() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username for the VideoGameSystems database: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        return DriverManager.getConnection("jdbc:mysql://localhost/video_game_systems", username, password);
    }
}
