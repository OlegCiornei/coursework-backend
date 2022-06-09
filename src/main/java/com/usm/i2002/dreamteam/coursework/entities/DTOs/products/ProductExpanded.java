package com.usm.i2002.dreamteam.coursework.entities.DTOs.products;

import com.usm.i2002.dreamteam.coursework.entities.AgeCategory;
import com.usm.i2002.dreamteam.coursework.entities.Gender;
import com.usm.i2002.dreamteam.coursework.entities.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@RequiredArgsConstructor
public class ProductExpanded extends ProductDto {

    private String description;
    private Gender gender;
    private AgeCategory ageCategory;

    public static ProductExpanded of(Product product) {
        return ProductExpanded.builder()
                .name(product.getName())
                .price(product.getPrice())
                .image(product.getImage())
                .gender(product.getGender())
                .category(product.getCategory())
                .description(product.getDescription())
                .ageCategory(product.getAgeCategory())
                .build();
    }

    public static Product to(ProductExpanded product) {
        return Product.builder()
                .name(product.getName())
                .price(product.getPrice())
                .image(product.getImage())
                .gender(product.getGender())
                .category(product.getCategory())
                .description(product.getDescription())
                .ageCategory(product.getAgeCategory())
                .build();
    }

}
