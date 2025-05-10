package com.example.cinema.repository.ticket;

import com.example.cinema.model.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс ITicketRepository на основе Spring Data JPA.
 */
@Repository
public interface ITicketRepository extends JpaRepository<Ticket, Long> {
    // При необходимости можно добавить:
    // List<Ticket> findBySessionId(Long sessionId);
}
