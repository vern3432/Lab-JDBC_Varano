package com.dblab;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PresentationLayer {
    private Connection mealPlanningConnection;
    private Connection arcadeGamesConnection;

    public PresentationLayer(Connection mealPlanningConnection, Connection arcadeGamesConnection) {
        this.mealPlanningConnection = mealPlanningConnection;
        this.arcadeGamesConnection = arcadeGamesConnection;
    }

    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Choose an option:");
            System.out.println("1. Running a single query against the meal planning database");
            System.out.println("2. Running GetRecipes stored procedure against the meal planning database");
            System.out.println("3. Running a new method that returns the results of a statement (ArcadeGames)");
            System.out.println("4. Running a new method that returns the results of a prepared statement (ArcadeGames)");
            System.out.println("5. Running a new method that returns the results of a callable statement (ArcadeGames)");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:
                    // Run a new method that returns the results of a statement (ArcadeGames)
                    ResultSet resultSet = ArcadeGamesDAL.executeStatement(arcadeGamesConnection, "SELECT * FROM games");
                    // Process result set
                    break;
                case 4:
                    // Run a new method that returns the results of a prepared statement (ArcadeGames)
                    ResultSet preparedResultSet = ArcadeGamesDAL.executePreparedStatement(arcadeGamesConnection, "SELECT * FROM games WHERE genre = ?", "Action");
                    // Process result set
                    break;
                case 5:
                    // Run a new method that returns the results of a callable statement (ArcadeGames)
                    ResultSet callableResultSet = ArcadeGamesDAL.executeCallableStatement(arcadeGamesConnection, "GetHighScores");
                    // Process result set
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Connection mealPlanningConnection = DataMgr.getMealPlanningConnection();
            Connection arcadeGamesConnection = DataMgr.getArcadeGamesConnection();

            PresentationLayer presentationLayer = new PresentationLayer(mealPlanningConnection, arcadeGamesConnection);
            presentationLayer.run();

            mealPlanningConnection.close();
            arcadeGamesConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
