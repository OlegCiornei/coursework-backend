package com.usm.i2002.dreamteam.coursework.entities.DTOs.products;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@RequiredArgsConstructor
public class ProductExpanded extends ProductDto {
    private String description;
}
