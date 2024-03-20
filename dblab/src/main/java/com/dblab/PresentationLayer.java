package com.dblab;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PresentationLayer {
    private Connection mealPlanningConnection;
    private Connection arcadeGamesConnection;
    private Connection videoGameSystemsConnection;

    public PresentationLayer(Connection mealPlanningConnection, Connection arcadeGamesConnection, Connection videoGameSystemsConnection) {
        this.mealPlanningConnection = mealPlanningConnection;
        this.arcadeGamesConnection = arcadeGamesConnection;
        this.videoGameSystemsConnection = videoGameSystemsConnection;
    }

    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Choose an option:");
            System.out.println("1. Running a single query against the meal planning database");
            System.out.println("2. Running GetRecipes stored procedure against the meal planning database");
            System.out.println("3. Running a new method that returns the results of a statement (ArcadeGames)");
            System.out.println("4. Running a new method that returns the results of a prepared statement (ArcadeGames)");
            System.out.println("5. Running a new method that returns the results of a callable statement (ArcadeGames)");
            System.out.println("6. Running a new method that returns the results of a statement (VideoGameSystems)");
            System.out.println("7. Running a new method that returns the results of a prepared statement (VideoGameSystems)");
            System.out.println("8. Running a new method that returns the results of a callable statement (VideoGameSystems)");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Implement choice 1 for meal planning database
                    runSingleQueryMealPlanning();
                    break;
                case 2:
                    // Implement choice 2 for meal planning database
                    runGetRecipesProcedureMealPlanning();
                    break;
                case 3:
                    // Run a new method that returns the results of a statement (ArcadeGames)
                    ResultSet resultSetArcadeGames = ArcadeGamesDAL.executeStatement(arcadeGamesConnection, "SELECT * FROM games");
                    // Process result set
                    break;
                case 4:
                    // Run a new method that returns the results of a prepared statement (ArcadeGames)
                    ResultSet preparedResultSetArcadeGames = ArcadeGamesDAL.executePreparedStatement(arcadeGamesConnection, "SELECT * FROM games WHERE genre = ?", "Action");
                    // Process result set
                    break;
                case 5:
                    // Run a new method that returns the results of a callable statement (ArcadeGames)
                    ResultSet callableResultSetArcadeGames = ArcadeGamesDAL.executeCallableStatement(arcadeGamesConnection, "GetHighScores");
                    // Process result set
                    break;
                case 6:
                    // Run a new method that returns the results of a statement (VideoGameSystems)
                    ResultSet resultSetVideoGameSystems = VideoGameSystemsDAL.executeStatement(videoGameSystemsConnection, "SELECT * FROM consoles");
                    // Process result set
                    break;
                case 7:
                    // Run a new method that returns the results of a prepared statement (VideoGameSystems)
                    ResultSet preparedResultSetVideoGameSystems = VideoGameSystemsDAL.executePreparedStatement(videoGameSystemsConnection, "SELECT * FROM consoles WHERE manufacturer = ?", "Nintendo");
                    // Process result set
                    break;
                case 8:
                    // Run a new method that returns the results of a callable statement (VideoGameSystems)
                    ResultSet callableResultSetVideoGameSystems = VideoGameSystemsDAL.executeCallableStatement(videoGameSystemsConnection, "GetTopRatedConsoles");
                    // Process result set
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void runSingleQueryMealPlanning() throws SQLException {
        // Implement choice 1 for meal planning database
        String sql = "SELECT * FROM table_name"; // Replace table_name with the actual table name
        try (ResultSet resultSet = mealPlanningConnection.createStatement().executeQuery(sql)) {
            // Process result set
            while (resultSet.next()) {
                // Process each row
            }
        }
    }

    private void runGetRecipesProcedureMealPlanning() throws SQLException {
        // Implement choice 2 for meal planning database
        String sql = "{CALL GetRecipes()}"; // Replace GetRecipes with the actual stored procedure name
        try (ResultSet resultSet = mealPlanningConnection.prepareCall(sql).executeQuery()) {
            // Process result set
            while (resultSet.next()) {
                // Process each row
            }
        }
    }

    public static void main(String[] args) {
        try {
            Connection mealPlanningConnection = DataMgr.getMealPlanningConnection();
            Connection arcadeGamesConnection = DataMgr.getArcadeGamesConnection();
            Connection videoGameSystemsConnection = DataMgr.getVideoGameSystemsConnection();
            PresentationLayer presentationLayer = (PresentationLayer) new PresentationLayer(mealPlanningConnection, arcadeGamesConnection, videoGameSystemsConnection);
            presentationLayer.run();

            mealPlanningConnection.close();
            arcadeGamesConnection.close();
            videoGameSystemsConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

