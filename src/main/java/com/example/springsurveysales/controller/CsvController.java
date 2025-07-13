package com.example.springsurveysales.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springsurveysales.entity.Survey;
import com.example.springsurveysales.repository.SurveyRepository;

@Controller
@RequestMapping("/admin")
public class CsvController {

    @Autowired
    private SurveyRepository surveyRepository;

    @GetMapping("/csv")
    public void exportCsv(HttpServletResponse response) throws IOException {
    	 try {
        response.setContentType("text/csv; charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"surveys.csv\"");
        response.setHeader("Content-Type", "text/csv; charset=UTF-8");

        PrintWriter writer = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8));
        writer.write('\uFEFF'); // BOM（バイトオーダーマーク）を追加

        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
            .withHeader("ID", "ユーザー名", "質問1", "質問2", "質問3", "回答日時"));

        List<Survey> surveys = surveyRepository.findAll();

            for (Survey survey : surveys) {
                csvPrinter.printRecord(
                    survey.getId(),
                    survey.getUsername(),
                    survey.getQuestion1(),
                    survey.getQuestion2(),
                    survey.getQuestion3(),
                    survey.getSubmittedAt()
                );
            }
            csvPrinter.flush();
            csvPrinter.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
