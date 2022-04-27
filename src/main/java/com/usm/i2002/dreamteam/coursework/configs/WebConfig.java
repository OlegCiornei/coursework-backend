package com.usm.i2002.dreamteam.coursework.configs;

import com.usm.i2002.dreamteam.coursework.converters.ProductDtoToProduct;
import com.usm.i2002.dreamteam.coursework.converters.ProductToProductDto;
import com.usm.i2002.dreamteam.coursework.converters.ProductToProductExpanded;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ProductToProductDto());
        registry.addConverter(new ProductDtoToProduct());
        registry.addConverter(new ProductToProductExpanded());
    }
}