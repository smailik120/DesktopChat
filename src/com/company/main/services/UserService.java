package com.company.main.services;

import com.company.main.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserService {
    private String table;
    private Connection connection;
    public UserService(String table, Connection connection) {
        this.table = table;
        this.connection = connection;
    }

    public String addUser(User user) {
        Statement statement;
        if(user.getLogin().length() >= 4 && user.getPassword().length() >= 4 && getUserByName(user) == null) {
            String request = "insert into " + table + "(login, password) values('" + user.getLogin() + "','" + user.getPassword() + "');";
            if (connection != null) {
                try {
                    statement = connection.createStatement();
                    statement.executeUpdate(request);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return "User add";
        }
        else if(user.getLogin().length() < 4 && user.getPassword().length() < 4) {
            return "login and password must be bigger than 3";
        }
        return "this user exist";
    }

    public User getUserByName(User user) {
        Statement statement;
        boolean bool = false;
        String request = "select * from " + this.table + " where login = '" + user.getLogin() + "' and password = '" + user.getPassword() +"';";
        if (connection != null) {
            try {
                statement = connection.createStatement();
                ResultSet resultSet;
                resultSet = statement.executeQuery(request);
                if(resultSet.next()) {
                    bool = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(bool == true) {
            return user;
        }
        else {
            return null;
        }
    }
}
