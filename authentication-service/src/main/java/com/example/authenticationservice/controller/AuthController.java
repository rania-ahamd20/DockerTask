package com.example.authenticationservice.controller;

import com.example.authenticationservice.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Controller;

@Controller
public class AuthController {

    @GetMapping("/")
    public String showLoginForm(Model model) {
        model.addAttribute("credentials", new Credentials());
        return "loginForm";
    }

    @PostMapping("/authenticate")
    public String authenticateUser(@RequestParam String username, @RequestParam String password, Model model) {
        if (UserService.authenticateUser(username, password)) {
            model.addAttribute("isSuccess", true);
            return "redirect:/insertData";
        } else {
            model.addAttribute("isSuccess", false);
            return "loginForm";
        }
    }
    @GetMapping("/insertData")
    public String showInsertDataPage() {
        return "insertData";
    }

}
