package com.example.chatbot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Data
@Entity
public class Options {

    @Id
    @GeneratedValue
    private int option_id;
    private String optionName;

    @Getter(AccessLevel.NONE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Question question;

    private int linkId;

    private String productName;

}
