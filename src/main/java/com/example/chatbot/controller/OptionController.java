package com.example.chatbot.controller;

import com.example.chatbot.model.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


public class OptionController {

    @Autowired
    private OptionRepository optionRepository;

    @GetMapping("/test/options")
    public List<Options> getAllOptions() {
        return optionRepository.findAll();

    }



   @PostMapping("/test/options")
    public ResponseEntity<Object> createOption(@Valid @RequestBody Options option) {
        Options savedOption = optionRepository.save(option);

        // Return the current request URL
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}") //appending id to the URL
                .buildAndExpand(savedOption.getOption_id()) // expand the URI
                .toUri();

        return ResponseEntity.created(location).build();
    }


}
