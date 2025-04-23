package com.cinema.util.exceptions;

/**
 * Исключение, выбрасываемое при ошибке удаления сеанса.
 */
public class SessionDeletionException extends Exception {
    public SessionDeletionException(String message) {
        super(message);
    }
}