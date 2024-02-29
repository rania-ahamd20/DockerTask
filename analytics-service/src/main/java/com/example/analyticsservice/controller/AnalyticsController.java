package com.example.analyticsservice.controller;

import com.example.analyticsservice.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @Autowired
    public AnalyticsController(AnalyticsService analyticsService) {

        this.analyticsService = analyticsService;
    }

    @PostMapping("/calculateAnalytics")
    public String calculateAnalytics(Model model) {
        analyticsService.calculateAnalytics();
        model.addAttribute("successMessage", "Analytics calculated and stored successfully!");

        return "insertData";
    }
}
