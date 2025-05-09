package com.example.cinema.util.exceptions;

/**
 * Исключение, выбрасываемое при ошибке удаления продукта.
 */
public class ProductDeletionException extends Exception {
    public ProductDeletionException(String message) {
        super(message);
    }
}