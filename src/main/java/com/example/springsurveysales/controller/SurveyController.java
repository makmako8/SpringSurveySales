package com.example.springsurveysales.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.springsurveysales.entity.Survey;
import com.example.springsurveysales.repository.SurveyRepository;

@Controller
public class SurveyController {

    @Autowired
    private SurveyRepository surveyRepository;

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("survey", new Survey());
        return "survey-form";
    }

    // アンケートの送信処理
    @PostMapping("/submit")
    public String submitSurvey(@ModelAttribute Survey survey,
                               @AuthenticationPrincipal UserDetails userDetails) {
        survey.setUsername(userDetails.getUsername());
        surveyRepository.save(survey);
        return "redirect:/thanks";
    }
    @GetMapping("/surveys")
    @ResponseBody
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }
    

    @GetMapping("/thanks")
    public String thanks() {
        return "thanks";
    }
}
