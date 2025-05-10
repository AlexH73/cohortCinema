package com.example.cinema.util.exceptions;

/**
 * Исключение, выбрасываемое при превышении максимального количества сеансов, за которые отвечает сотрудник.
 */
public class MaxSessionsExceededException extends Exception {
    public MaxSessionsExceededException(String message) {
        super(message);
    }
}