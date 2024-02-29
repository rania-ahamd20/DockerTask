package com.example.showresultsservice.controller;

import com.example.showresultsservice.service.ShowResultsService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/results")
public class ResultsController {

    private final ShowResultsService showResultsService;

    @Autowired
    public ResultsController(ShowResultsService showResultsService) {
        this.showResultsService = showResultsService;
    }

    @GetMapping("/ShowResults")
    public String showResults(Model model) {
        List<Document> results = showResultsService.retrieveAndShowAnalytics();
        model.addAttribute("results", results);
        return "ShowResults";
    }
}
