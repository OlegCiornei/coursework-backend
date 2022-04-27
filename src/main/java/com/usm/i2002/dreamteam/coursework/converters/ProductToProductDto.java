package com.usm.i2002.dreamteam.coursework.converters;

import com.usm.i2002.dreamteam.coursework.entities.DTOs.products.ProductDto;
import com.usm.i2002.dreamteam.coursework.entities.Product;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

public class ProductToProductDto implements Converter<Product, ProductDto> {
    @Override
    public ProductDto convert(@NonNull Product source) {
        return ProductDto.builder()
                .name(source.getName())
                .image(source.getImage())
                .price(source.getPrice())
                .build();
    }
}
