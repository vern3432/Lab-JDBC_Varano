package com.dblab;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PresentationAndDataAccessLayer {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Connection connection = DataAccessLayer.getArcadeGamesConnection();

            System.out.println("Choose a table to insert data into:");
            List<String> tables = getTables(connection);
            for (int i = 0; i < tables.size(); i++) {
                System.out.println((i + 1) + ". " + tables.get(i));
            }
            int tableChoice = scanner.nextInt();
            if (tableChoice < 1 || tableChoice > tables.size()) {
                System.out.println("Invalid table choice!");
                return;
            }
            String tableName = tables.get(tableChoice - 1);

            List<String> columns = getColumns(connection, tableName);
            System.out.println("Columns in table '" + tableName + "':");
            for (int i = 0; i < columns.size(); i++) {
                System.out.println((i + 1) + ". " + columns.get(i));
            }

            System.out.println("Choose columns to insert data into (comma-separated, e.g., 1,3,5):");
            String columnChoicesInput = scanner.next();
            String[] columnChoices = columnChoicesInput.split(",");
            List<String> selectedColumns = new ArrayList<>();
            for (String choice : columnChoices) {
                int columnIndex = Integer.parseInt(choice) - 1;
                if (columnIndex >= 0 && columnIndex < columns.size()) {
                    selectedColumns.add(columns.get(columnIndex));
                }
            }

            insertData(connection, tableName, selectedColumns);

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getTables(Connection connection) throws SQLException {
        List<String> tables = new ArrayList<>();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getTables(null, null, null, new String[]{"TABLE"});
        while (resultSet.next()) {
            tables.add(resultSet.getString("TABLE_NAME"));
        }
        return tables;
    }

    public static List<String> getColumns(Connection connection, String tableName) throws SQLException {
        List<String> columns = new ArrayList<>();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getColumns(null, null, tableName, null);
        while (resultSet.next()) {
            columns.add(resultSet.getString("COLUMN_NAME"));
        }
        return columns;
    }

    public static void insertData(Connection connection, String tableName, List<String> columns) {
        try (Scanner scanner = new Scanner(System.in)) {
            StringBuilder sqlBuilder = new StringBuilder("INSERT INTO ");
            sqlBuilder.append(tableName).append(" (");
            for (int i = 0; i < columns.size(); i++) {
                sqlBuilder.append(columns.get(i));
                if (i < columns.size() - 1) {
                    sqlBuilder.append(", ");
                }
            }
            sqlBuilder.append(") VALUES (");
            for (int i = 0; i < columns.size(); i++) {
                sqlBuilder.append("?");
                if (i < columns.size() - 1) {
                    sqlBuilder.append(", ");
                }
            }
            sqlBuilder.append(")");
            String sql = sqlBuilder.toString();

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                for (int i = 0; i < columns.size(); i++) {
                    System.out.println("Enter value for column '" + columns.get(i) + "':");
                    String value = scanner.next();
                    statement.setString(i + 1, value);
                }
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
