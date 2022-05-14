package com.usm.i2002.dreamteam.coursework.entities.DTOs.products;

import com.usm.i2002.dreamteam.coursework.entities.Category;
import com.usm.i2002.dreamteam.coursework.entities.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@RequiredArgsConstructor
public class ProductDto {

    private String name;
    private String image;
    private Double price;
    private Category category;

    public static ProductDto of(Product product) {
        return ProductDto.builder()
                .name(product.getName())
                .image(product.getImage())
                .price(product.getPrice())
                .category(product.getCategory())
                .build();
    }
}
