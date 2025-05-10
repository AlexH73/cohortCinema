package com.example.cinema.util.exceptions;

public class NotEnoughProductsInStockException extends Exception {
    public NotEnoughProductsInStockException(String message) {
        super(message);
    }
}
