package com.example.springsurveysales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.springsurveysales.repository.SurveyRepository;

@Controller
public class AdminController {

    @Autowired
    private SurveyRepository surveyRepository;

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("surveys", surveyRepository.findAll());
        return "admin-dashboard";
    }
}