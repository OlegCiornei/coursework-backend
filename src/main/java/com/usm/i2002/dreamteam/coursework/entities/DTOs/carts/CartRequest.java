package com.usm.i2002.dreamteam.coursework.entities.DTOs.carts;

import com.usm.i2002.dreamteam.coursework.entities.Cart;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CartRequest {
    private final String productName;
    private final Long amount;

    public Cart to(final String userEmail) {
        return Cart.builder()
                .productName(productName)
                .userEmail(userEmail)
                .amount(amount)
                .build();
    }

}
