package com.example.chatbot.controller;

import com.example.chatbot.model.Question;
import com.example.chatbot.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class QuestionController {

  private  static boolean flag=false;
  private static String optionalModules[];

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private OptionRepository optionRepository;
    @Autowired
    QuestionService questionService;

    @GetMapping("/question")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return new ResponseEntity<List<Question>>(questionRepository.findAll(),HttpStatus.OK);

    }

    @GetMapping("/question/{id}")
    public ResponseEntity<Optional<Question>> findOne(@PathVariable int id) {
        if(id==1){
            flag=true;
        }
        return new ResponseEntity<Optional<Question>>(questionRepository.findById(id),HttpStatus.OK);
    }

    @GetMapping("/question/{qid}/{oid}")
    public ResponseEntity<Optional<Question>> findNextQuestion(@PathVariable int qid,@PathVariable String oid) {
          boolean validOpt=questionService.validateOption(qid,oid);
          if (validOpt) {
              int choice = Integer.parseInt(oid);
              int nextQuestion =optionRepository.find(oid,qid);
              if (nextQuestion != 0) {
                  return new ResponseEntity<Optional<Question>>(questionRepository.findById(nextQuestion), HttpStatus.OK);
              }
              else{
                  if (flag){
                  return new ResponseEntity<Optional<Question>>(HttpStatus.FOUND);
                  }else{
                      return new ResponseEntity<Optional<Question>>(HttpStatus.BAD_REQUEST);
                  }
              }
          }
       else{
              return new ResponseEntity<Optional<Question>>(HttpStatus.BAD_REQUEST);
       }

    }


    @PostMapping("/test/questions")
    public ResponseEntity<Object> createQuestion(@Valid @RequestBody Question question) {
        Question savedQuestion = questionRepository.save(question);

        // Return the current request URL
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}") //appending id to the URL
                .buildAndExpand(savedQuestion.getId()) // expand the URI
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
