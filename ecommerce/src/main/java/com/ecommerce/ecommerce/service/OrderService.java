package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.Cart;
import com.ecommerce.ecommerce.model.OrderEntity;
import com.ecommerce.ecommerce.model.OrderItem;
import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.repository.CartRepository;
import com.ecommerce.ecommerce.repository.OrderItemRepository;
import com.ecommerce.ecommerce.repository.OrderRepository;
import com.ecommerce.ecommerce.repository.ProductRepository;
import com.ecommerce.ecommerce.dto.InvoiceItemResponse;
import com.ecommerce.ecommerce.dto.InvoiceResponse;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    // GENERATE INVOICE
public InvoiceResponse getInvoice(Long orderId) {

    OrderEntity order =
            orderRepository.findById(orderId)
                    .orElseThrow(
                            () -> new RuntimeException(
                                    "Order not found"
                            )
                    );

    List<OrderItem> orderItems =
            orderItemRepository.findByOrderId(orderId);

    List<InvoiceItemResponse> invoiceItems =
            new ArrayList<>();

    for(OrderItem item : orderItems) {

        Product product =
                productRepository.findById(
                        item.getProductId()
                ).orElse(null);

        if(product == null) {

            InvoiceItemResponse deletedProduct =
                    new InvoiceItemResponse(
                            "Product Deleted",
                            0,
                            item.getQuantity(),
                            0
                    );

            invoiceItems.add(deletedProduct);
            continue;
        }

        InvoiceItemResponse invoiceItem =
                new InvoiceItemResponse(
                        product.getName(),
                        product.getPrice(),
                        item.getQuantity(),
                        product.getPrice()
                                * item.getQuantity()
                );

        invoiceItems.add(invoiceItem);
    }

    return new InvoiceResponse(
            order.getId(),
            order.getUserId(),
            order.getTotalAmount(),
            invoiceItems
    );
}


    public OrderService(CartRepository cartRepository,
                        ProductRepository productRepository,
                        OrderRepository orderRepository,
                        OrderItemRepository orderItemRepository) {

        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    // PLACE ORDER
    public OrderEntity placeOrder(Long userId) {

        List<Cart> cartItems =
                cartRepository.findByUserId(userId);

        if(cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        double total = 0;

        // 1. CHECK STOCK + CALCULATE TOTAL
        for(Cart cart : cartItems) {

            Product product =
                    productRepository.findById(
                            cart.getProductId()
                    ).orElseThrow(
                            () -> new RuntimeException(
                                    "Product not found"
                            )
                    );

            if(product.getStock() < cart.getQuantity()) {

                throw new RuntimeException(
                        "Not enough stock for "
                                + product.getName()
                );
            }

            total += product.getPrice()
                    * cart.getQuantity();
        }

        // 2. SAVE ORDER
        OrderEntity order =
                new OrderEntity();

        order.setUserId(userId);
        order.setTotalAmount(total);

        OrderEntity savedOrder =
                orderRepository.save(order);

        // 3. SAVE ORDER ITEMS + REDUCE STOCK
        for(Cart cart : cartItems) {

            Product product =
                    productRepository.findById(
                            cart.getProductId()
                    ).orElseThrow();

            OrderItem item =
                    new OrderItem();

            item.setOrderId(savedOrder.getId());
            item.setProductId(product.getId());
            item.setQuantity(cart.getQuantity());

            orderItemRepository.save(item);

            int newStock =
                    product.getStock()
                            - cart.getQuantity();

            product.setStock(newStock);

            productRepository.save(product);
        }

        // 4. CLEAR CART
        cartRepository.deleteAll(cartItems);

        return savedOrder;
    }

    // ORDER HISTORY
    public List<OrderEntity> getOrdersByUserId(Long userId) {

        return orderRepository.findByUserId(userId);
    }
}