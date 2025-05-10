package com.example.cinema.model.session;

import com.example.cinema.model.film.Film;
import com.example.cinema.model.hall.Hall;
import com.example.cinema.model.ticket.Ticket;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
/**
 * Класс Session представляет собой сеанс показа фильма в конкретном зале и времени.
 */
public class Session implements ISession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;

    @ManyToOne
    @JoinColumn(name = "cinema_hall_id", nullable = false)
    private Hall cinemaHall;

    @Column(name = "date_time_start", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "date_time_end", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "ticket_price", nullable = false)
    private double ticketPrice;

    @Column(name = "seats", nullable = false)
    private int availableSeats;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<Ticket> tickets = new HashSet<>();

    public Session(Film film, Hall cinemaHall, LocalDateTime startTime, double ticketPrice) {
        validate(film, cinemaHall, ticketPrice);
        this.film = film;
        this.cinemaHall = cinemaHall;
        this.startTime = startTime;
        this.ticketPrice = ticketPrice;
        this.endTime = calculateEndTime();
    }

    private void validate(Film film, Hall cinemaHall, double ticketPrice) {
        if (film == null || cinemaHall == null) {
            throw new IllegalArgumentException("Фильм и зал не могут быть null.");
        }
        if (ticketPrice <= 0) {
            throw new IllegalArgumentException("Цена билета должна быть > 0.");
        }
    }

    private LocalDateTime calculateEndTime() {
        if (film != null) {
            return startTime.plusMinutes(film.getDurationMinutes());
        }
        return startTime; // Или можно выбрасывать исключение, если film == null
    }

    // Реализация методов интерфейса
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Film getFilm() {
        return film;
    }

    @Override
    public void setFilm(Film film) {
        this.film = film;
        this.endTime = calculateEndTime(); // Присваиваем новое значение endTime
    }

    @Override
    public Hall getCinemaHall() {
        return cinemaHall;
    }

    @Override
    public void setCinemaHall(Hall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    @Override
    public LocalDateTime getStartTime() {
        return startTime;
    }

    @Override
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public List<Ticket> getTickets() {
        return new ArrayList<>(tickets);
    }

    @Override
    public void addTicket(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException("Билет не может быть null.");
        }
        tickets.add(ticket);
    }

    @Override
    public void removeTicket(Ticket ticket) {
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
    public int getAvailableSeats() {
        return availableSeats;
    }

    @Override
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id='" + id + '\'' +
                ", film=" + (film != null ? film.getTitle() : "null") + // Проверка на null
                ", cinemaHall=" + (cinemaHall != null ? cinemaHall.getHallNumber() : "null") + // Проверка на null
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", ticketPrice=" + ticketPrice +
                ", tickets=" + tickets.size() +
                '}';
    }
}