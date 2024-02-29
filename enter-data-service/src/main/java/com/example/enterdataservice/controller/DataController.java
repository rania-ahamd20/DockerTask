package com.example.enterdataservice.controller;

import com.example.enterdataservice.db.DBConnector;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Controller
public class DataController {

    @GetMapping("/DataForm")
    public String DataForm() {
        return "insertData";
    }

    @PostMapping("/insert")
    public String insert(@RequestParam int studentId, @RequestParam float grade, Model model) {
        try (Connection connection = DBConnector.getConnection()) {
            String sql = "INSERT INTO Grades (StudentID, Grade) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, studentId);
                preparedStatement.setFloat(2, grade);
                preparedStatement.executeUpdate();
            }
            model.addAttribute("successMessage", "Data inserted successfully!");
        } catch (SQLException e) {
            model.addAttribute("errorMessage", "Error inserting data. Please try again.");
        }
        return "insert";
    }
}
