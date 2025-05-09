package com.cinema.util.exceptions;

/**
 * Бросается, когда билет с заданным ID не найден.
 */
public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(Long ticketId) {
        super("Ticket not found with ID: " + ticketId);
    }
}
