package Singleton;

import java.sql.*;

public class singleton {

    private static singleton instance = null;
    private Connection connection;

    private singleton() {

    }


    public static singleton getInstance() {
        if (instance == null) {
            instance = new singleton();
        }
        return instance;
    }

    public void connect() {
        try {

            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Pm1508nvm_*");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void execute(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet result(String query) {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}