package com.example.cinema.service.ticket;

import com.example.cinema.model.ticket.Ticket;
import com.example.cinema.model.user.Customer;

import java.util.List;

public interface ITicketService {

    /**
     * Получить билет по ID.
     */
    Ticket findById(Long id);

    /**
     * Сохранить билет.
     */
    Ticket save(Ticket ticket);

    /**
     * Удалить билет по ID.
     */
    void deleteById(Long id);

    /**
     * Получить все билеты.
     */
    List<Ticket> findAll();

    /**
     * Получить все билеты пользователя.
     */
    List<Ticket> findByCustomer(Customer customer);

    /**
     * Проверить доступность билета.
     */
    boolean isAvailable(Long ticketId);
}
