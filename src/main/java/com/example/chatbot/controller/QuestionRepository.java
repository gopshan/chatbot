package com.example.chatbot.controller;

import com.example.chatbot.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface QuestionRepository extends JpaRepository<Question,Integer> {




}
