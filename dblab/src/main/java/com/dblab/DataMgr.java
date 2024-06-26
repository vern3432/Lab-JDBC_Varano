package com.dblab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DataMgr {
    // Connection to MealPlanning Database
    public static Connection getMealPlanningConnection() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username for the MealPlanning database: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        return DriverManager.getConnection("jdbc:mysql://localhost/meal_planning", username, password);
    }

    // Connection to ArcadeGames Database
    public static Connection getArcadeGamesConnection() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username for the ArcadeGames database: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        return DriverManager.getConnection("jdbc:mysql://localhost/arcade_games", username, password);
    }

    // Connection to VideoGameSystems Database
    public static Connection getVideoGameSystemsConnection() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username for the VideoGameSystems database: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        return DriverManager.getConnection("jdbc:mysql://localhost/video_game_systems", username, password);
    }
}
