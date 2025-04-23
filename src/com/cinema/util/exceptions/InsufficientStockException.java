package com.cinema.util.exceptions;

/**
 * Исключение, выбрасываемое при недостаточном количестве товара на складе.
 */
public class InsufficientStockException extends Exception {
    public InsufficientStockException(String message) {
        super(message);
    }
}