package com.usm.i2002.dreamteam.coursework.services;

import com.usm.i2002.dreamteam.coursework.entities.Product;

import org.springframework.data.domain.Page;

public interface ProductService {
    Page<Product> getAll(Integer pageNumber, Integer pageSize, String sortBy);

    Product getByName(String name);

    Page<Product> searchByName(String name, Integer pageNumber, Integer pageSize);

    Product addProduct(Product product);
}
