package com.cinema.model.session;

import com.cinema.model.film.Film;
import com.cinema.model.hall.Hall;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Класс Session представляет собой сеанс показа фильма в конкретном зале и времени.
 */
public class Session {
    private final String id;
    private Film film;
    private Hall hall;
    private LocalDateTime startTime;
    private double ticketPrice;

    public Session(double ticketPrice, LocalDateTime startTime, Hall hall, Film film) {
        this.id = UUID.randomUUID().toString();
        this.ticketPrice = ticketPrice;
        this.startTime = startTime;
        this.hall = hall;
        this.film = film;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id='" + id + '\'' +
                ", film=" + film +
                ", hall=" + hall +
                ", startTime=" + startTime +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}
