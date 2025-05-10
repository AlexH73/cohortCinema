package com.example.cinema.util.exceptions;

/**
 * Бросается, когда заказ с заданным ID не найден.
 */
public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long orderId) {
        super("Order not found with ID: " + orderId);
    }
}

