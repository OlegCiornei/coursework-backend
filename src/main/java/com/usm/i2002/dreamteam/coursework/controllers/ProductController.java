package com.usm.i2002.dreamteam.coursework.controllers;

import java.util.List;

import com.usm.i2002.dreamteam.coursework.entities.Product;
import com.usm.i2002.dreamteam.coursework.services.ProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.getAll());
    }

}