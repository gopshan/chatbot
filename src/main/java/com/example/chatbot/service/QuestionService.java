package com.example.chatbot.service;

import com.example.chatbot.controller.OptionRepository;
import com.example.chatbot.controller.QuestionRepository;
import com.example.chatbot.model.Options;
import com.example.chatbot.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class QuestionService {


    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private OptionRepository optionRepository;

   public boolean validateOption(int id1, String id2) {
       try {
           int id = Integer.parseInt(id2);
           Optional<Question> quest = questionRepository.findById(id1);

           List<Options> opt = quest.get().getOptions();
           int size = opt.size();
           if (id != 0)
               if (id <= size)
                   return true;
       }catch (Exception e){

           System.out.println("Invalid Input");
       }

        return false;
    }

    public int findLinkId(String opt_id){

       int id=Integer.parseInt(opt_id);

       Options opt=optionRepository.getOne(id);

       int linkId=opt.getLinkId();


       return linkId;

    }


}
