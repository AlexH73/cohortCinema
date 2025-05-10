package com.example.cinema.model.ticket;

import com.example.cinema.model.hall.Seat;
import com.example.cinema.model.session.Session;
import com.example.cinema.model.user.Customer;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

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

    public Ticket(Session session, Customer user, Seat seat, double basePrice) {
        validate(session, seat, basePrice);
        this.session = session;
        this.user = user;
        this.seat = seat;
        this.price = calculateFinalPrice(basePrice);
        this.status = TicketStatus.AVAILABLE;
    }

    public Ticket() {}

    private void validate(Session session, Seat seat, double price) {
        if (session == null || seat == null || price <= 0) {
            throw new IllegalArgumentException("Некорректные параметры билета.");
        }
    }

    public double calculateFinalPrice(double basePrice) {
        double finalPrice = basePrice;

        // По времени суток (день/вечер/ночь)
        int hour = session.getStartTime().getHour();
        if (hour >= 6 && hour < 16) {
            finalPrice *= 0.8; // дневной
        } else if (hour >= 16 && hour < 22) {
            finalPrice *= 1.0; // вечерний
        } else {
            finalPrice *= 1.2; // ночной
        }

        // Скидка студентам/пенсионерам
        if (user != null && (user.isStudent() || user.isRetired())) {
            finalPrice *= 0.8;
        }

        return finalPrice;
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public Seat getSeat() {
        return seat;
    }

    @Override
    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
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
                "id=" + id +
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
        return Double.compare(ticket.price, price) == 0 &&
                Objects.equals(id, ticket.id) &&
                Objects.equals(session, ticket.session) &&
                Objects.equals(user, ticket.user) &&
                Objects.equals(seat, ticket.seat) &&
                status == ticket.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, session, user, seat, price, status);
    }
}