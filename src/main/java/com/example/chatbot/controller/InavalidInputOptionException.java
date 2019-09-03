package com.example.chatbot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InavalidInputOptionException extends RuntimeException{


    public InavalidInputOptionException(String s) {
    }


}
