package com.cinema.model.ticket;

import com.cinema.model.hall.Seat;
import com.cinema.model.session.ISession;
import com.cinema.model.user.Customer;

import java.util.Objects;
import java.util.UUID;

/**
 * Класс Ticket представляет билет, купленный на определённый сеанс.
 */
public class Ticket implements ITicket {
    private final String id;
    private ISession session;
    private Customer user;
    private Seat seat;
    private double price;
    private TicketStatus status;

    public Ticket(ISession session, Customer user, Seat seat, double price) {
        validate(session, seat, price);
        this.id = UUID.randomUUID().toString();
        this.session = session;
        this.user = user;
        this.price = price;
        this.status = TicketStatus.AVAILABLE;
    }

    private void validate(ISession session, Seat seat, double price) {
        if (session == null || seat == null || price <= 0) {
            throw new IllegalArgumentException("Некорректные параметры билета.");
        }
    }

    // Реализация методов интерфейса ITicket
    @Override
    public ISession getSession() {
        return session;
    }

    @Override
    public void setSession(ISession session) {
        if (session == null) {
            throw new IllegalArgumentException("Сеанс не может быть null.");
        }
        this.session = session;
    }

    @Override
    public Seat getSeat() {
        return seat;
    }

    @Override
    public void setSeat(Seat seat) {
        if (seat == null) {
            throw new IllegalArgumentException("Место не может быть null.");
        }
        this.seat = seat;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Цена должна быть > 0.");
        }
        this.price = price;
    }

    @Override
    public TicketStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public Customer getUser() {
        return user;
    }

    public void setUser(Customer user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", session=" + session +
                ", seat=" + seat +
                ", price=" + price +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Double.compare(ticket.price, price) == 0 && id.equals(ticket.id) && session.equals(ticket.session) && user.equals(ticket.user) && seat.equals(ticket.seat) && status == ticket.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, session, user, seat, price, status);
    }
}