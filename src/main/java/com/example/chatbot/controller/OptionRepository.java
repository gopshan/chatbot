package com.example.chatbot.controller;

import com.example.chatbot.model.Options;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Options,Integer> {
}
