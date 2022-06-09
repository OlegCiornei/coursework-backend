package com.usm.i2002.dreamteam.coursework.services;

import com.usm.i2002.dreamteam.coursework.entities.Product;
import com.usm.i2002.dreamteam.coursework.entities.TestResult;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Product getByName(final String name);

    Page<Product> getAll(final Integer pageNumber, final Integer pageSize, final String sortBy);

    Page<Product> searchByName(final String name, final Integer pageNumber, final Integer pageSize);

    Product addProduct(final Product product);

    List<Product> getByTestResults(final TestResult testResult);
}
