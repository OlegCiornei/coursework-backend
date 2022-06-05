package com.usm.i2002.dreamteam.coursework.controllers;

import com.usm.i2002.dreamteam.coursework.entities.DTOs.carts.CartRequest;
import com.usm.i2002.dreamteam.coursework.entities.DTOs.carts.CartResponse;
import com.usm.i2002.dreamteam.coursework.services.CartService;
import io.swagger.annotations.ApiImplicitParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.usm.i2002.dreamteam.coursework.entities.DTOs.carts.CartResponse.of;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    @PreAuthorize("hasAuthority('users:read')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class)
    public ResponseEntity<List<CartResponse>> getCart() {
        final String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return ResponseEntity.ok(cartService.getCart(email).stream().map(CartResponse::of).collect(toList()));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('users:read')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class)
    public ResponseEntity<CartResponse> addCartItem(final @RequestBody CartRequest cartRequest) {
        final String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return ResponseEntity.status(CREATED).body(of(cartService.addCartItem(cartRequest.to(email))));
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('users:read')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class)
    public ResponseEntity<CartResponse> deleteCartItem(final @RequestBody CartRequest cartRequest) {
        final String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return ResponseEntity.ok(of(cartService.deleteCartItem(email, cartRequest.getProductName(), cartRequest.getAmount())));
    }
}
