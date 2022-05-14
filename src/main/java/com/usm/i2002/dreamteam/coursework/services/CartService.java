package com.usm.i2002.dreamteam.coursework.services;

import com.usm.i2002.dreamteam.coursework.entities.Cart;

import java.util.List;

public interface CartService {
    List<Cart> getCart(final String email);

    Cart addCart(final Cart cart);
}
