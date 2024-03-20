package com.dblab;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataMgr {
    // Connection to MealPlanning Database
    public static Connection getMealPlanningConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/meal_planning", "newuser1", "3634");
    }

    // Connection to ArcadeGames Database
    public static Connection getArcadeGamesConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/arcade_games", "newuser1", "3634");
    }

    // Connection to VideoGameSystems Database
    public static Connection getVideoGameSystemsConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/video_game_systems", "newuser1", "3634");
    }
}