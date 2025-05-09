package com.example.cinema.util.exceptions;

/**
 * Исключение, выбрасываемое при ошибке бронирования билета.
 */
public class BookingException extends Exception {
    public BookingException(String message) {
        super(message);
    }
}