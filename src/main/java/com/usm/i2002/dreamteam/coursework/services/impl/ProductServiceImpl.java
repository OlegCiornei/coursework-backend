package com.usm.i2002.dreamteam.coursework.services.impl;

import com.usm.i2002.dreamteam.coursework.entities.Category;
import com.usm.i2002.dreamteam.coursework.entities.Product;
import com.usm.i2002.dreamteam.coursework.entities.TestResult;
import com.usm.i2002.dreamteam.coursework.exceptions.NoSuchProductException;
import com.usm.i2002.dreamteam.coursework.repositories.ProductRepository;
import com.usm.i2002.dreamteam.coursework.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.usm.i2002.dreamteam.coursework.entities.AgeCategory.getAgeCategory;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product getByName(final String name) {
        return productRepository.findByNameIgnoreCase(name).orElseThrow(() -> new IllegalArgumentException("No such product"));
    }

    @Override
    public Page<Product> getAll(final Integer pageNumber, final Integer pageSize, final String sortBy) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return productRepository.findAll(page);
    }

    @Override
    public Page<Product> searchByName(final String name, final Integer pageNumber, final Integer pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
        Page<Product> foundProducts = productRepository.findByNameContainsIgnoreCase(name, page);

        if (foundProducts.isEmpty())
            throw new NoSuchProductException("No such product");

        return foundProducts;
    }

    @Override
    public Product addProduct(final Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getByTestResults(final TestResult testResult) {
        if (testResult.getTestResults().values().stream().anyMatch(value -> value < 0))
            throw new IllegalArgumentException("Negative amount of gifts");

        List<Product> gifts = new ArrayList<>();
        testResult.getTestResults().forEach((key, value) -> gifts.addAll(productRepository.findProductsByTestResults(key.name(), testResult.getGender().name(), getAgeCategory(testResult.getAge()).name(), value)));

        return gifts;
    }

    @Override
    public Page<Product> searchByCategory(final String category, final Integer pageNumber, final Integer pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
        final Category actualCategory;
        try {
            actualCategory = Category.valueOf(category.toUpperCase(Locale.ROOT));
        } catch (Exception e) {
            throw new IllegalArgumentException("Illegal category");
        }

        Page<Product> foundProducts = productRepository.findByCategory(actualCategory, page);

        if (foundProducts.isEmpty())
            throw new NoSuchProductException("No such product");

        return foundProducts;
    }
}
