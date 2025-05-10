package com.example.cinema.model.ticket;

import com.example.cinema.model.hall.Seat;
import com.example.cinema.model.session.Session;
import com.example.cinema.model.user.Customer;
import jakarta.persistence.*;

import java.util.Objects;

/**
 * Класс Ticket представляет билет, купленный на определённый сеанс.
 */
@Entity
@Table(name = "ticket")
public class Ticket implements ITicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Customer user;

    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;


    private double price;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    public Ticket(Session session, Customer user, Seat seat, double price) {
        validate(session, seat, price);
        this.session = session;
        this.user = user;
        this.price = price;
        this.status = TicketStatus.AVAILABLE;
    }

    // Конструктор без параметров для Hibernate
    public Ticket() {}

    private void validate(Session session, Seat seat, double price) {
        if (session == null || seat == null || price <= 0) {
            throw new IllegalArgumentException("Некорректные параметры билета.");
        }
    }

    // Реализация методов интерфейса ITicket
    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public void setSession(Session session) {
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