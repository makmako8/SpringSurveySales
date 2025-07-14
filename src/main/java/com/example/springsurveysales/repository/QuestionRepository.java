package com.example.springsurveysales.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springsurveysales.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
