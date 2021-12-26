package com.usm.i2002.dreamteam.coursework.entities.DTOs.products;

import com.usm.i2002.dreamteam.coursework.entities.Product;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ProductExpanded extends ProductDto {
    private String description;

    public static ProductExpanded toProductExpanded(Product product) {
        return ProductExpanded.builder()
                .description(product.getDescription())
                .name(product.getName())
                .image(product.getImage())
                .price(product.getPrice())
                .build();
    }
}
