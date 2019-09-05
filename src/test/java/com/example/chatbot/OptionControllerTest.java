package com.example.chatbot;

import com.example.chatbot.controller.OptionController;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.Assert.*;

import java.io.FileReader;
import java.io.IOException;

public class OptionControllerTest {
    /*@Test
    public void createOptionTest()throws IOException, ParseException {
        final JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader("src/main/java/option.json");
        OptionController oc = new OptionController();
        ResponseEntity<Object> result = oc.createOption(null);
        if (result.getStatusCode() == HttpStatus.CREATED) {
            if (result.getHeaders().containsKey("location") && result.getHeaders().get("location").equals("https://localhost:9090/")) {

            }
        }
    }*/
}
