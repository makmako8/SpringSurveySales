package com.example.springsurveysales.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.springsurveysales.entity.Survey;
import com.example.springsurveysales.repository.QuestionRepository;
import com.example.springsurveysales.repository.SurveyRepository;
import com.example.springsurveysales.service.MailService;

@Controller
public class SurveyController {
	
	@Autowired
	private QuestionRepository questionRepository;

    @Autowired
    private SurveyRepository surveyRepository;
    
    @Autowired
    private MailService mailService;

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("survey", new Survey());
        model.addAttribute("questions", questionRepository.findAll());
        return "survey-form";
    }

    // アンケートの送信処理
    @PostMapping("/submit")
    public String submitSurvey(@RequestParam("answers") List<String> answers,
                               @AuthenticationPrincipal UserDetails userDetails) {
    	Survey survey = new Survey();
        survey.setUsername(userDetails.getUsername());
        survey.setAnswers(answers);
        surveyRepository.save(survey);
        
        // メール通知を送信
        String subject = "【通知】新しいアンケートが届きました";
        String text = "新しいアンケートが送信されました。ユーザー名：" + userDetails.getUsername();
        
        // 実際の通知先メールアドレスを指定
        mailService.sendMail("ellieringo52@gmail.com", subject, text);
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
