package com.cinema.util.exceptions;

public class NotEnoughProductsInStockException extends Exception {
    public NotEnoughProductsInStockException(String message) {
        super(message);
    }
}
