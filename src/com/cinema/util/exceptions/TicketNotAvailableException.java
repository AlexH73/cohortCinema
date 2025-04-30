package com.cinema.util.exceptions;

/**
 * Бросается, когда пытаются добавить в заказ билет, который уже недоступен.
 */
public class TicketNotAvailableException extends RuntimeException {
    public TicketNotAvailableException(Long ticketId) {
        super("Ticket not available with ID: " + ticketId);
    }
}
