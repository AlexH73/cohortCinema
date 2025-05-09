package com.example.cinema.model.session;

import com.example.cinema.model.film.Film;
import com.example.cinema.model.hall.Hall;
import com.example.cinema.model.ticket.Ticket;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Интерфейс ISession предоставляет контракт для класса, представляющего сеанс в системе управления кинотеатром.
 */
public interface ISession {

    Long getId();

    /**
     * Получить фильм, который будет показываться во время этого сеанса.
     *
     * @return Фильм этого сеанса.
     */
    Film getFilm();

    /**
     * Установить фильм для этого сеанса.
     *
     * @param film Фильм для показа во время сеанса.
     */
    void setFilm(Film film);

    /**
     * Получить кинозал, в котором будет проходить сеанс.
     *
     * @return Кинозал этого сеанса.
     */
    Hall getCinemaHall();

    /**
     * Установить кинозал для этого сеанса.
     *
     * @param cinemaHall Кинозал для проведения сеанса.
     */
    void setCinemaHall(Hall cinemaHall);

    /**
     * Получить время начала сеанса.
     *
     * @return Время начала сеанса.
     */
    LocalDateTime getStartTime();

    /**
     * Установить время начала сеанса.
     *
     * @param startTime Время начала сеанса.
     */
    void setStartTime(LocalDateTime startTime);

    /**
     * Получить время окончания сеанса.
     *
     * @return Время окончания сеанса.
     */
    LocalDateTime getEndTime();

    /**
     * Получить список всех билетов для этого сеанса.
     *
     * @return Список билетов для сеанса.
     */
    List<Ticket> getTickets();

    /**
     * Добавить новый билет в список билетов сеанса.
     *
     * @param ticket Новый билет для добавления в сеанс.
     */
    void addTicket(Ticket ticket);

    /**
     * Удалить билет из списка билетов сеанса.
     *
     * @param ticket Билет для удаления из сеанса.
     */
    void removeTicket(Ticket ticket);

    /**
     *  Возвращает цену билета на данный сеанс.
     *
     * @return Цена билета.
     */
    double getTicketPrice();

    /**
     * Устанавливает цену билета на данный сеанс.
     *
     * @param price Новая цена билета.
     */
    void setTicketPrice(double price);

    /**
     *  Возвращает количество доступных мест на данный сеанс.
     *
     * @return Количество доступных мест.
     */
    int getAvailableSeats();

    void setAvailableSeats(int availableSeats);
}