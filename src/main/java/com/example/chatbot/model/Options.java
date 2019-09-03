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


    public String getOption_no() {
        return option_no;
    }

    public void setOption_no(String option_no) {
        this.option_no = option_no;
    }

    private String option_no;

    private String optionName;

    @Getter(AccessLevel.NONE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Question question;

    private int linkId;

    private String productName;

    @JsonIgnore
    public int getOption_id() {
        return option_id;
    }

    public String getOptionName() {
        return optionName;
    }

    @JsonIgnore
    public Question getQuestion() {
        return question;
    }

    @JsonIgnore
    public int getLinkId() {
        return linkId;
    }

    @JsonIgnore
    public String getProductName() {
        return productName;
    }
}
