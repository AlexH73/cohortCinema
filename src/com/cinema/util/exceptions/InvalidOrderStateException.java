package com.cinema.util.exceptions;

/**
 * Бросается, когда действие над заказом невозможно из-за его текущего статуса.
 */
public class InvalidOrderStateException extends RuntimeException {
  public InvalidOrderStateException(String message) {
    super(message);
  }
}

