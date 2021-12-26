package com.usm.i2002.dreamteam.coursework.controllers;

import com.usm.i2002.dreamteam.coursework.entities.DTOs.products.ProductDto;
import com.usm.i2002.dreamteam.coursework.entities.DTOs.products.ProductExpanded;
import com.usm.i2002.dreamteam.coursework.services.ProductService;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductDto>> getAll(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "name") String sortBy) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.getAll(pageNumber, pageSize, sortBy)
                        .map(ProductDto::toProductDto));
    }

    @GetMapping("/{name}")
    public ResponseEntity<ProductDto> getByName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ProductExpanded.toProductExpanded(productService.getByName(name)));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProductDto>> searchByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.searchByName(name, pageNumber, pageSize)
                        .map(ProductDto::toProductDto));
    }
}