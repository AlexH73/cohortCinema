package com.cinema.model.ticket;

import com.cinema.model.session.Session;
import com.cinema.model.user.User;

import java.util.UUID;

/**
 * Класс Ticket представляет билет, купленный на определённый сеанс.
 */
public class Ticket {
    private final String id;
    private Session session;
    private User user;
    private int row;
    private int seat;
    private boolean isPaid;

    public Ticket(boolean isPaid, int row, int seat, Session session, User user) {
        this.id = UUID.randomUUID().toString();
        this.isPaid = false; // по умолчанию билет не оплачен
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

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
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
