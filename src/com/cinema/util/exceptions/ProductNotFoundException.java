package com.cinema.util.exceptions;

/**
 * Бросается, когда продукт с заданным ID не найден.
 */
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long productId) {
        super("Product not found with ID: " + productId);
    }
}
