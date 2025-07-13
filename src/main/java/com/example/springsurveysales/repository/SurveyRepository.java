package com.example.springsurveysales.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springsurveysales.entity.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    List<Survey> findByUsername(String username);
}
