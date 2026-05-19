package com.ecommerce.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {

    private Long cartId;
    private Long productId;
    private String productName;
    private double price;
    private int quantity;
    private double subtotal;
}