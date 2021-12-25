package com.usm.i2002.dreamteam.coursework.services.impl;

import java.util.List;

import com.usm.i2002.dreamteam.coursework.entities.Product;
import com.usm.i2002.dreamteam.coursework.repositories.ProductRepository;
import com.usm.i2002.dreamteam.coursework.services.ProductService;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
