package com.usm.i2002.dreamteam.coursework.services;

import com.usm.i2002.dreamteam.coursework.entities.Product;
import org.springframework.data.domain.Page;

public interface ProductService {

    Page<Product> getAll(final Integer pageNumber, final Integer pageSize, final String sortBy);

    Page<Product> searchByName(final String name, final Integer pageNumber, final Integer pageSize);

    Product addProduct(final Product product);
}
