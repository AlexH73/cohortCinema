package com.example.cinema.util.exceptions;

/**
 * Исключение, выбрасываемое при ошибке создания продукта.
 */
public class ProductCreationException extends Exception {
    public ProductCreationException(String message) {
        super(message);
    }
}