package com.example.springsurveysales.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

@Entity
public class Survey {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String username; // 回答者（ユーザー名）
    private String question1;
    private String question2;
    private String question3;
    // ... 最大10問程度まで追加

    private LocalDateTime submittedAt;
    
    @PrePersist
    protected void onCreate() {
        submittedAt = LocalDateTime.now();
    }
    // getter/setter略
    public Long getId() {
    	return id;
    }
    public void setId(Long id) {
    	this.id = id;
    }
    public String getUsername() {
    	return username;
    }
    public void setUsername(String username) {
    	this.username = username;
    }
    public String getQuestion1() {
    	return question1;
    }
    public void setQuestion1(String question1) {
    	this.question1 = question1;
    }
    public String getQuestion2() {
    	return question2;
    }
    public void setQuestion2(String question2) {
    	this.question2 = question2;
    }
    public String getQuestion3() {
    	return question3;
    }
    public void setQuestion3(String question3) {
    	this.question3 = question3;
    }
    public LocalDateTime getSubmittedAt() {
    	return submittedAt;
    }
    public void setSubmittedAt(LocalDateTime submittedAt) {
    	this.submittedAt = submittedAt;
    }
}
