package com.usm.i2002.dreamteam.coursework.converters;

import com.usm.i2002.dreamteam.coursework.entities.DTOs.products.ProductExpanded;
import com.usm.i2002.dreamteam.coursework.entities.Product;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

public class ProductToProductExpanded implements Converter<Product, ProductExpanded> {
    @Override
    public ProductExpanded convert(@NonNull Product source) {
        return ProductExpanded.builder()
                .name(source.getName())
                .price(source.getPrice())
                .image(source.getImage())
                .description(source.getDescription())
                .build();
    }
}
