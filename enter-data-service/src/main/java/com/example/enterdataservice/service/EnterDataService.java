package com.example.enterdataservice.service;

import com.example.enterdataservice.db.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EnterDataService {

    public void enterGrade(int studentId, float grade) {
        try (Connection connection = DBConnector.getConnection()) {
            String insertQuery = "INSERT INTO Grades (StudentID, Grade) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, studentId);
                preparedStatement.setFloat(2, grade);
                preparedStatement.executeUpdate();
                System.out.println("Grade entered successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
