package com.example.cinema.util.exceptions;

/**
 * Исключение, выбрасываемое при ошибке создания фильма.
 */
public class FilmCreationException extends Exception {
    public FilmCreationException(String message) {
        super(message);
    }
}
