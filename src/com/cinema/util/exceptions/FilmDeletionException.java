package com.cinema.util.exceptions;

/**
 * Исключение, выбрасываемое при ошибке удаления фильма.
 */
public class FilmDeletionException extends Exception {
    public FilmDeletionException(String message) {
        super(message);
    }
}