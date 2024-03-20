package com.dblab;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataMgr {
    // Connection to MealPlanning Database
    public static Connection getMealPlanningConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/meal_planning", "username", "password");
    }

    // Connection to ArcadeGames Database
    public static Connection getArcadeGamesConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/arcade_games", "username", "password");
    }

    // Connection to VideoGameSystems Database
    public static Connection getVideoGameSystemsConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/video_game_systems", "username", "password");
    }
}