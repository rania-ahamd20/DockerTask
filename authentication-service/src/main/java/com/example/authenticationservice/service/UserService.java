package com.example.authenticationservice.service;

import com.example.authenticationservice.db.DBConnector;
import com.example.authenticationservice.model.User;
import com.example.authenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class UserService {

    static String jdbcUrl = DBConnector.getJdbcUrl();
    static String dbUsername = DBConnector.getUsername();
    static String dbPassword = DBConnector.getPassword();

    public static boolean authenticateUser(String username, String password) {
        try (Connection connection = DBConnector.getConnection()) {
            String query = "SELECT * FROM user_auth  WHERE username = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next() && resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
