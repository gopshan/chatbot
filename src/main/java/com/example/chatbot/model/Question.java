package com.example.chatbot.model;

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

    protected Question() {

    }

    public Question(int id, String questionName) {
        this.id = id;
        this.questionName = questionName;
    }

}
