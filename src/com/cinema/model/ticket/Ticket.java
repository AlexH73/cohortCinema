package com.cinema.model.ticket;

import com.cinema.model.session.ISession;
import com.cinema.model.user.Customer;
import com.cinema.model.user.User;
import java.util.UUID;

/**
 * Класс Ticket представляет билет, купленный на определённый сеанс.
 */
public class Ticket implements ITicket {
    private final String id;
    private ISession session;
    private User user;
    private int row;
    private int seatNumber;
    private double price;
    private TicketStatus status;

    public Ticket(ISession session, Customer user, int row, int seatNumber, double price) {
        validate(session, row, seatNumber, price);
        this.id = UUID.randomUUID().toString();
        this.session = session;
        this.user = user;
        this.row = row;
        this.seatNumber = seatNumber;
        this.price = price;
        this.status = TicketStatus.AVAILABLE;
    }

    private void validate(ISession session, int row, int seatNumber, double price) {
        if (session == null || row <= 0 || seatNumber <= 0 || price <= 0) {
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
    public int getSeatNumber() {
        return seatNumber;
    }

    @Override
    public void setSeatNumber(int seatNumber) {
        if (seatNumber <= 0) {
            throw new IllegalArgumentException("Номер места должен быть > 0.");
        }
        this.seatNumber = seatNumber;
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
    public String getStatus() {
        return status.name();
    }

    @Override
    public void setStatus(String status) {
        this.status = TicketStatus.valueOf(status.toUpperCase());
    }

    // Дополнительные методы (не из интерфейса)
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        if (row <= 0) {
            throw new IllegalArgumentException("Ряд должен быть > 0.");
        }
        this.row = row;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", session=" + session +
                ", row=" + row +
                ", seat=" + seatNumber +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}