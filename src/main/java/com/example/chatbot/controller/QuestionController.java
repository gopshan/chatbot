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

  private  boolean flag=false;
  private int optionalModules[]=new int[3];

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
    public ResponseEntity<Optional<Question>> findOneQuestion(@PathVariable int id) {

        return new ResponseEntity<Optional<Question>>(questionRepository.findById(id),HttpStatus.OK);
    }

    @GetMapping("/question/{qid}/{oid}")
    public ResponseEntity<Optional<Question>> findNextQuestion1(@PathVariable int qid,@PathVariable String oid) {
          boolean validOpt=questionService.validateOption(qid,oid);
          if (validOpt) {
              int nextQuestion =optionRepository.find(oid,qid);
              if (nextQuestion != 0) {
                  return new ResponseEntity<Optional<Question>>(questionRepository.findById(nextQuestion), HttpStatus.OK);
              }
              else{
                  return new ResponseEntity<Optional<Question>>(HttpStatus.FOUND);

              }
          }
       else{
              return new ResponseEntity<Optional<Question>>(HttpStatus.BAD_REQUEST);
       }

    }

    @GetMapping("/question/{qid}/{oid1}/{oid2}")
    public ResponseEntity<Optional<Question>> findNextQuestion2(@PathVariable int qid,@PathVariable String oid1,@PathVariable String oid2) {
        int nextQuestion1 =optionRepository.find(oid1,qid);
        boolean validOpt=questionService.validateOption(nextQuestion1,oid2);
        if (validOpt) {
            int optionId=optionRepository.findOptionId(oid2,nextQuestion1);
          /*  optionalModules[2]=optionId;*/
            int nextQuestion2 =optionRepository.find(oid2,nextQuestion1);
            if (nextQuestion2 != 0) {
                return new ResponseEntity<Optional<Question>>(questionRepository.findById(nextQuestion2), HttpStatus.OK);
            }
            else{
                if (flag){
                    return new ResponseEntity<Optional<Question>>(HttpStatus.FOUND);
                }else{
                    return new ResponseEntity<Optional<Question>>(HttpStatus.NOT_FOUND);
                }
            }
        }
        else{
            return new ResponseEntity<Optional<Question>>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/question/{qid}/{oid1}/{oid2}/{oid3}")
    public ResponseEntity<String> findMonitorName(@PathVariable int qid,@PathVariable String oid1,@PathVariable String oid2,@PathVariable String oid3) {
       int nextQuestion1 =optionRepository.find(oid1,qid);
       int nextQuestion2=optionRepository.find(oid2,nextQuestion1);
        boolean validOpt=questionService.validateOption(nextQuestion2,oid3);
        if (validOpt) {
            int optionId=optionRepository.findOptionId(oid3,nextQuestion2);
            /*optionalModules[3]=optionId;*/
                if (flag){
                    String str=optionRepository.findMonitor(oid3,nextQuestion2);
                    return new ResponseEntity<String>(optionRepository.findMonitor(oid3,nextQuestion2),HttpStatus.NOT_FOUND);

                }else{
                    return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
                }
            }
        else{
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping("/add/question")
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
