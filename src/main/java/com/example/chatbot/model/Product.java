package com.example.chatbot.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter @Setter @ToString
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Integer id;
    private String productName;

    @OneToMany(mappedBy = "product")
    private List<Options> options;

    protected Product() {

    }

    public Product(int id, String productName) {
        this.id = id;
        this.productName = productName;
    }

}
