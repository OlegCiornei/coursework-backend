package com.usm.i2002.dreamteam.coursework.entities.DTOs.products;

import com.usm.i2002.dreamteam.coursework.entities.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@RequiredArgsConstructor
public class ProductExpanded extends ProductDto {

    private String description;

    public static ProductExpanded of(Product product) {
        return ProductExpanded.builder()
                .name(product.getName())
                .image(product.getImage())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
    }

    public static Product to(ProductExpanded product) {
        return Product.builder()
                .name(product.getName())
                .image(product.getImage())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
    }

}
