package com.usm.i2002.dreamteam.coursework.services.impl;

import com.usm.i2002.dreamteam.coursework.entities.Product;
import com.usm.i2002.dreamteam.coursework.exceptions.NoSuchProductException;
import com.usm.i2002.dreamteam.coursework.repositories.ProductRepository;
import com.usm.i2002.dreamteam.coursework.services.ProductService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Page<Product> getAll(Integer pageNumber, Integer pageSize, String sortBy) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return productRepository.findAll(page);
    }

    @Override
    public Product getByName(String name) {
        return productRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new NoSuchProductException("No such product"));
    }

    @Override
    public Page<Product> searchByName(String name, Integer pageNumber, Integer pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
        Page<Product> foundProducts = productRepository.findByNameContainsIgnoreCase(name, page);

        if (foundProducts.isEmpty())
            throw new NoSuchProductException("No such product");

        return foundProducts;
    }
}
