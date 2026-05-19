package com.ecommerce.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceResponse {

    private Long orderId;
    private Long userId;
    private double totalAmount;
    private List<InvoiceItemResponse> items;
}