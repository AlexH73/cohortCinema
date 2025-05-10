package com.example.cinema.util.exceptions;

/**
 * Исключение, выбрасываемое при ошибке создания сеанса.
 */
public class SessionCreationException extends Exception {
    public SessionCreationException(String message) {
        super(message);
    }
}