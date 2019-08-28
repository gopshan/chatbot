package com.example.chatbot.controller;

import com.example.chatbot.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductContoller {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/test/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();

    }

    @GetMapping("/test/products/{id}")
    public Optional<Product> findOne(@PathVariable int id) {
        return productRepository.findById(id);
    }
}
