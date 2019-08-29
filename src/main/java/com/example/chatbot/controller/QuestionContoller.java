package com.example.chatbot.controller;

import com.example.chatbot.model.Options;
import com.example.chatbot.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
/*import org.springframework.hateoas.Resource;*/

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class QuestionContoller {


    private static String optionalModules[];

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private OptionRepository optionRepository;

    @GetMapping("/test/questions")
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();

    }

    @GetMapping("/question/{id}")
    public Optional<Question> findOne(@PathVariable int id) {
        return questionRepository.findById(id);
    }

    @GetMapping("/question/{id1}/{id2}")
    public Optional<Question> findOne(@PathVariable int id1,@PathVariable int id2) {

        Optional<Question> quest=questionRepository.findById(id1);

        List<Options> list=new ArrayList<Options>();
        list=quest.get().getOptions();

        Options opt=optionRepository.getOne(id2);

        if(opt!=null){

            int linkid=opt.getLinkId();

            if(linkid!=0){



            }


        }


        return questionRepository.findById(id1);
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

  /*  @GetMapping("/jpa/question/{id}")
    public Resource<Question> getOneUser(@PathVariable int id) {
        Optional<Question> question = questionRepository.findById(id);
        if(!question.isPresent())
            throw new QuestionNotFoundException("id-"+ id);

        // HATEOS - creates hyperlink
        Resource<Question> resource = new Resource<Question>(question.get());

        //enable to create links from methods
        ControllerLinkBuilder linkTo =
                linkTo(methodOn(this.getClass()).getAllQuestions());
        resource.add(linkTo.withRel("all-questions"));

        return resource;
    }*/



}
