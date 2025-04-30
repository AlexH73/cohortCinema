package com.cinema.util.exceptions;

/**
 * Бросается, когда пытаются добавить в заказ продукт в количестве, превышающем остаток.
 */
public class ProductNotAvailableException extends RuntimeException {
    public ProductNotAvailableException(Long productId) {
        super("Product not available (insufficient stock) with ID: " + productId);
    }
}
