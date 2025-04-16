package com.cinema.model.session;

import com.cinema.model.film.IFilm;
import com.cinema.model.hall.ICinemaHall;
import com.cinema.model.ticket.ITicket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Класс Session представляет собой сеанс показа фильма в конкретном зале и времени.
 */
public class Session implements ISession {
    private final String id;
    private IFilm film;
    private ICinemaHall cinemaHall;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double ticketPrice;
    private List<ITicket> tickets = new ArrayList<>();

    public Session(IFilm film, ICinemaHall cinemaHall, LocalDateTime startTime, double ticketPrice) {
        validate(film, cinemaHall, ticketPrice);
        this.id = UUID.randomUUID().toString();
        this.film = film;
        this.cinemaHall = cinemaHall;
        this.startTime = startTime;
        this.ticketPrice = ticketPrice;
        calculateEndTime();
    }

    private void validate(IFilm film, ICinemaHall cinemaHall, double ticketPrice) {
        if (film == null || cinemaHall == null) {
            throw new IllegalArgumentException("Фильм и зал не могут быть null.");
        }
        if (ticketPrice <= 0) {
            throw new IllegalArgumentException("Цена билета должна быть > 0.");
        }
    }

    private void calculateEndTime() {
        if (film != null) {
            this.endTime = startTime.plusMinutes(film.getDuration());
        }
    }

    // Реализация методов интерфейса
    @Override
    public IFilm getFilm() {
        return film;
    }

    @Override
    public void setFilm(IFilm film) {
        this.film = film;
        calculateEndTime();
    }

    @Override
    public ICinemaHall getCinemaHall() {
        return cinemaHall;
    }

    @Override
    public void setCinemaHall(ICinemaHall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    @Override
    public LocalDateTime getStartTime() {
        return startTime;
    }

    @Override
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        calculateEndTime();
    }

    @Override
    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public List<ITicket> getTickets() {
        return tickets;
    }

    @Override
    public void addTicket(ITicket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException("Билет не может быть null.");
        }
        tickets.add(ticket);
    }

    @Override
    public void removeTicket(ITicket ticket) {
        tickets.remove(ticket);
    }

    @Override
    public double getTicketPrice() {
        return ticketPrice;
    }

    @Override
    public void setTicketPrice(double ticketPrice) {
        if (ticketPrice <= 0) {
            throw new IllegalArgumentException("Цена билета должна быть > 0.");
        }
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id='" + id + '\'' +
                ", film=" + film.getName() +
                ", cinemaHall=" + cinemaHall.getHallNumber() +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}
