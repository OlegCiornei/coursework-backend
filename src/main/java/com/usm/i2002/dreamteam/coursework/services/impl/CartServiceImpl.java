package com.usm.i2002.dreamteam.coursework.services.impl;

import com.usm.i2002.dreamteam.coursework.entities.Cart;
import com.usm.i2002.dreamteam.coursework.repositories.CartRepository;
import com.usm.i2002.dreamteam.coursework.services.CartService;
import com.usm.i2002.dreamteam.coursework.services.ProductService;
import com.usm.i2002.dreamteam.coursework.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final UserService userService;
    private final ProductService productService;
    private final CartRepository cartRepository;

    @Override
    public List<Cart> getCart(final String email) {
        return cartRepository.findByUser_EmailIgnoreCase(email);
    }

    @Override
    public Cart addCart(final Cart cart) {
        if (cart.getAmount() < 1)
            throw new IllegalArgumentException("Illegal amount");

        try {
            final Optional<Cart> foundCart = cartRepository.findByUserEmailIgnoreCaseAndProductNameIgnoreCase(cart.getUserEmail(), cart.getProductName());

            if (foundCart.isPresent()) {
                final Cart newCart = foundCart.get();
                newCart.setAmount(cart.getAmount() + newCart.getAmount());
                return cartRepository.save(newCart);
            } else {
                cart.setProduct(productService.getByName(cart.getProductName()));
                cart.setUser(userService.getByEmail(cart.getUserEmail()));
                return cartRepository.save(cart);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
