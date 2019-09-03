package com.example.chatbot.controller;

import com.example.chatbot.model.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/*@Repository*/
public class OptionsRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

   /* public Options findOption(int id1,int id2) {
        return jdbcTemplate.queryForObject("select option_id from Options where option_no=? and question_id?", new Object[] { id1 },new Object[] { id2 },
                new BeanPropertyRowMapper<Options>(Options.class));
    }*/
}
