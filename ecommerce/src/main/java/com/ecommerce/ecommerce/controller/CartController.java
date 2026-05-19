package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.CartResponse;
import com.ecommerce.ecommerce.model.Cart;
import com.ecommerce.ecommerce.service.CartService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {

        this.cartService = cartService;
    }

    // ADD TO CART
    @PostMapping("/add")
    public Cart addToCart(@RequestBody Cart cart) {

        return cartService.addToCart(cart);
    }

    // GET CART BY USER ID
    @GetMapping("/{userId}")
    public List<CartResponse> getCart(
            @PathVariable Long userId) {

        return cartService.getCartByUserId(userId);
    }

    // REMOVE ITEM FROM CART
    @DeleteMapping("/item/{id}")
    public String removeItem(
            @PathVariable Long id) {

        cartService.removeItem(id);

        return "Item removed from cart";
    }
}