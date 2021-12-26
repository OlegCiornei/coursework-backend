package com.usm.i2002.dreamteam.coursework.entities.DTOs.products;

import com.usm.i2002.dreamteam.coursework.entities.Product;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ProductDto {
    private String name;
    private String image;
    private Double price;

    public static ProductDto toProductDto(Product product) {
        return ProductDto.builder()
                .name(product.getName())
                .image(product.getImage())
                .price(product.getPrice())
                .build();
    }
}
