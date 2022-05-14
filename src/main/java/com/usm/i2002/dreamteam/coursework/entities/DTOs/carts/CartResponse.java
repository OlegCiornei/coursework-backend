package com.usm.i2002.dreamteam.coursework.entities.DTOs.carts;

import com.usm.i2002.dreamteam.coursework.entities.Cart;
import com.usm.i2002.dreamteam.coursework.entities.DTOs.products.ProductDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CartResponse {
    private final ProductDto products;
    private final Long amount;

    public static CartResponse of(final Cart cart) {
        return new CartResponse(ProductDto.of(cart.getProduct()), cart.getAmount());
    }
}
