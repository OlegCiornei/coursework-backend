package com.usm.i2002.dreamteam.coursework.controllers;

import com.usm.i2002.dreamteam.coursework.entities.Category;
import com.usm.i2002.dreamteam.coursework.entities.DTOs.products.ProductDto;
import com.usm.i2002.dreamteam.coursework.entities.DTOs.products.ProductExpanded;
import com.usm.i2002.dreamteam.coursework.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductDto>> getAll(
            final @RequestParam(defaultValue = "0") Integer pageNumber,
            final @RequestParam(defaultValue = "10") Integer pageSize,
            final @RequestParam(defaultValue = "name") String sortBy) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.getAll(pageNumber, pageSize, sortBy).map(ProductDto::of));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProductDto>> searchByName(final @RequestParam String name,
                                                         final @RequestParam(defaultValue = "0") Integer pageNumber,
                                                         final @RequestParam(defaultValue = "10") Integer pageSize) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.searchByName(name, pageNumber, pageSize).map(ProductExpanded::of));
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<ProductDto> addProduct(final @RequestBody ProductExpanded product) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ProductExpanded.of(productService.addProduct(ProductExpanded.to(product))));
    }

    @PostMapping("/test/results")
    public ResponseEntity<List<ProductDto>> getGiftsByTestResults(final @RequestBody Map<Category, Integer> testResults) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(productService.getByTestResults(testResults).stream().map(ProductDto::of).collect(Collectors.toList()));
    }

}