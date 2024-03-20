package com.dblab;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArcadeGamesDAL {
    // Method to execute a simple statement
    public static ResultSet executeStatement(Connection connection, String query) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        return statement.executeQuery();
    }

    // Method to execute a prepared statement
    public static ResultSet executePreparedStatement(Connection connection, String query, String parameter) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, parameter);
        return preparedStatement.executeQuery();
    }

    // Method to execute a callable statement
    public static ResultSet executeCallableStatement(Connection connection, String procedureName) throws SQLException {
        return connection.prepareCall("{call " + procedureName + "}").executeQuery();
    }
}
