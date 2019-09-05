package com.example.chatbot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter @Setter @ToString
@Entity
public class Question {

    @Id
    @GeneratedValue
    private Integer id;
    private String questionName;

    @OneToMany(mappedBy = "question")
    private List<Options> options;

   /* public List<Options> getOptions() {
        return options;
    }*/

    public void setOptions(List<Options> options) {
        this.options = options;
    }



    protected Question() {

    }

    public Question(int id, String questionName) {
        this.id = id;
        this.questionName = questionName;
    }

    public Integer getId() {
        return id;
    }

    public List<Options> getOptions() {
        return options;
    }
}
