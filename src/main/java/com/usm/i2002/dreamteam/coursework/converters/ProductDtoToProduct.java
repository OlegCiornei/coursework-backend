package com.usm.i2002.dreamteam.coursework.converters;

import com.usm.i2002.dreamteam.coursework.entities.DTOs.products.ProductDto;
import com.usm.i2002.dreamteam.coursework.entities.DTOs.products.ProductExpanded;
import com.usm.i2002.dreamteam.coursework.entities.Product;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoToProduct implements Converter<ProductDto, Product> {
    @Override
    public Product convert(@NonNull ProductDto source) {
        Product product = Product.builder()
                .name(source.getName())
                .image(source.getImage())
                .price(source.getPrice())
                .build();

        if (source instanceof ProductExpanded) {
            ProductExpanded productExpanded = (ProductExpanded) source;
            product.setDescription(productExpanded.getDescription());
        }

        return product;
    }
}
