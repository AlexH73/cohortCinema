package com.cinema.model.ticket;

import com.cinema.model.session.ISession;
import com.cinema.model.session.Session;
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
    private int seat;
    private boolean isPaid;

    public Ticket(boolean isPaid, int row, int seat, Session session, User user) {
        this.id = UUID.randomUUID().toString();
        this.isPaid = isPaid;
        this.row = row;
        this.seat = seat;
        this.session = session;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public int getSeatNumber() {
        return seat;
    }

    @Override
    public void setSeatNumber(int seatNumber) {
        this.seat = seatNumber;
    }

    @Override
    public ISession getSession() {
        return session;
    }

    @Override
    public void setSession(ISession session) {
        if (session instanceof Session) {
            this.session = (Session) session;
        } else {
            throw new IllegalArgumentException("Session must be instance of Session class");
        }
    }

    @Override
    public double getPrice() {
        return session != null ? session.getTicketPrice() : 0.0;
    }

    @Override
    public void setPrice(double price) {
        if (session != null) {
            session.setTicketPrice(price);
        }
    }

    @Override
    public String getStatus() {
        return isPaid ? "Продано" : "Доступно";
    }

    @Override
    public void setStatus(String status) {
        this.isPaid = "Продано".equalsIgnoreCase(status);
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
                ", user=" + user +
                ", row=" + row +
                ", seat=" + seat +
                ", paid=" + isPaid +
                '}';
    }
}
