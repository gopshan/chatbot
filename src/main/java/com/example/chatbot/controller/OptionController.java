package com.example.chatbot.controller;

import com.example.chatbot.model.Options;
import com.example.chatbot.model.Question;
import com.example.chatbot.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.List;
import java.util.Optional;


public class OptionController {

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    QuestionService questionService;

    @GetMapping("/options")
    public List<Options> getAllOptions() {
        return optionRepository.findAll();

    }


    @PostMapping("/add/option")
    public ResponseEntity<Object> createOption(@Valid @RequestBody Options option) {
        Options savedOption = optionRepository.save(option);

        // Return the current request URL
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}") //appending id to the URL
                .buildAndExpand(savedOption.getOption_id()) // expand the URI
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/questions/{id1}/{id2}")
    public void findOneQuestion(@PathVariable int id1, @PathVariable String id2) {
     System.out.println("fsdfsffds");
     final String uri = "http://localhost:9090/question/5";

     RestTemplate restTemplate = new RestTemplate();
     String result = restTemplate.getForObject(uri, String.class);

    }

}
