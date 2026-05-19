package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.CartResponse;
import com.ecommerce.ecommerce.model.Cart;
import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.repository.CartRepository;
import com.ecommerce.ecommerce.repository.ProductRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository,
                       ProductRepository productRepository) {

        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    // ADD TO CART
    public Cart addToCart(Cart cart) {

        return cartRepository.save(cart);
    }

    // GET CART BY USER WITH PRODUCT DETAILS
    public List<CartResponse> getCartByUserId(Long userId) {

        List<Cart> cartItems =
                cartRepository.findByUserId(userId);

        List<CartResponse> response =
                new ArrayList<>();

        for(Cart cart : cartItems) {

            Product product =
                    productRepository.findById(
                            cart.getProductId()
                    ).orElse(null);

            if(product == null) {

                CartResponse deletedProduct =
                        new CartResponse(
                                cart.getId(),
                                cart.getProductId(),
                                "Product Deleted",
                                0,
                                cart.getQuantity(),
                                0
                        );

                response.add(deletedProduct);
                continue;
            }

            CartResponse cartResponse =
                    new CartResponse(
                            cart.getId(),
                            product.getId(),
                            product.getName(),
                            product.getPrice(),
                            cart.getQuantity(),
                            product.getPrice()
                                    * cart.getQuantity()
                    );

            response.add(cartResponse);
        }

        return response;
    }

    // REMOVE ITEM
    public void removeItem(Long id) {

        cartRepository.deleteById(id);
    }
}