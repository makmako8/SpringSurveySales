package com.example.springsurveysales.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springsurveysales.entity.Question;
import com.example.springsurveysales.repository.QuestionRepository;

@Controller
@RequestMapping("/admin/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping
    public String listQuestions(Model model) {
        model.addAttribute("questions", questionRepository.findAll());
        return "admin/questions/list";
    }

    @GetMapping("/new")
    public String newQuestion(Model model) {
        model.addAttribute("question", new Question());
        return "admin/questions/new";
    }

    @PostMapping("/save")
    public String saveQuestion(@ModelAttribute Question question) {
        questionRepository.save(question);
        return "redirect:/admin/questions";
    }

    @GetMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable Long id) {
        questionRepository.deleteById(id);
        return "redirect:/admin/questions";
    }
}
