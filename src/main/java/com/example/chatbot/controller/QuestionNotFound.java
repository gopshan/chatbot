package com.example.chatbot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
class QuestionNotFoundException extends RuntimeException {
    /* In this way there is no option that
     *  we can have custom messages.
     * */
    // Over riding the exception
    public QuestionNotFoundException(String s) {
        super(s);
    }
}
