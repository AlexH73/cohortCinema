package com.example.cinema.service.ticket;

import com.example.cinema.model.ticket.Ticket;
import com.example.cinema.model.ticket.TicketStatus;
import com.example.cinema.model.user.Customer;
import com.example.cinema.model.user.Role;
import com.example.cinema.repository.ticket.ITicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements ITicketService {

    private final ITicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(ITicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket findById(Long id) {
        Optional<Ticket> ticketOpt = ticketRepository.findById(id);
        return ticketOpt.orElse(null);
    }

    @Override
    public Ticket save(Ticket ticket) {
        applyDynamicPrice(ticket);
        applyDiscount(ticket);
        return ticketRepository.save(ticket);
    }

    @Override
    public void deleteById(Long id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> findByCustomer(Customer customer) {
        return customer.getBookingHistory();
    }

    @Override
    public boolean isAvailable(Long ticketId) {
        Ticket ticket = findById(ticketId);
        return ticket != null && ticket.getStatus() == TicketStatus.AVAILABLE;
    }

    /**
     * Применяет скидку 20% для студентов и пенсионеров.
     */
    private void applyDiscount(Ticket ticket) {
        Customer customer = ticket.getUser();
        if (customer != null) {
            Role role = customer.getRole();
            if (role == Role.STUDENT || role == Role.PENSIONER) {
                double discounted = ticket.getPrice() * 0.8;
                ticket.setPrice(discounted);
            }
        }
    }

    /**
     * Устанавливает цену на основе времени суток.
     */
    private void applyDynamicPrice(Ticket ticket) {
        LocalTime sessionTime = ticket.getSession().getStartTime().toLocalTime();

        if (isInRange(sessionTime, LocalTime.of(6, 0), LocalTime.of(17, 59))) {
            ticket.setPrice(10.0); // Дневной
        } else if (isInRange(sessionTime, LocalTime.of(18, 0), LocalTime.of(22, 59))) {
            ticket.setPrice(15.0); // Вечерний
        } else {
            ticket.setPrice(20.0); // Ночной
        }
    }

    /**
     * Проверка, можно ли сдать билет (не позже, чем за 5 минут до начала).
     */
    public boolean canRefund(Ticket ticket) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime sessionStart = ticket.getSession().getStartTime();
        return now.isBefore(sessionStart.minusMinutes(5));
    }

    private boolean isInRange(LocalTime time, LocalTime start, LocalTime end) {
        if (start.isBefore(end)) {
            return !time.isBefore(start) && !time.isAfter(end);
        } else {
            return !time.isBefore(start) || !time.isAfter(end); // ночной диапазон через полночь
        }
    }
}
