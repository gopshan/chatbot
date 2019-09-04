package com.example.chatbot.controller;

import com.example.chatbot.model.Options;
import com.example.chatbot.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OptionRepository extends JpaRepository<Options,Integer> {


    @Query("select linkId from Options where option_no=(:id1) and question_id=(:id2)")
    public int find(@Param("id1") String id1, @Param("id2") int id2);


    @Query("select productName from Options where option_no=(:id1) and question_id=(:id2)")
    public String findMonitor(@Param("id1") String id1, @Param("id2") int id2);


    @Query("select option_id from Options where option_no=(:id1) and question_id=(:id2)")
    public int findOptionId(@Param("id1") String id1, @Param("id2") int id2);
}
