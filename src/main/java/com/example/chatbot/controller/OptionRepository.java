package com.example.chatbot.controller;

import com.example.chatbot.model.Options;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OptionRepository extends JpaRepository<Options,Integer> {


    @Query("select linkId from Options where option_no=(:id1) and question_id=(:id2)")
    public int find(@Param("id1") String id1, @Param("id2") int id2);


    @Query("select productName from Options where option_no=(:id1) and question_id=(:id2)")
    public int findMonitor(@Param("id1") String id1, @Param("id2") int id2);

}
