package com.cinema.model.session;

import com.cinema.model.film.IFilm;
import com.cinema.model.hall.ICinemaHall;
import com.cinema.model.ticket.ITicket;
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
    IFilm getFilm();

    /**
     * Установить фильм для этого сеанса.
     *
     * @param film Фильм для показа во время сеанса.
     */
    void setFilm(IFilm film);

    /**
     * Получить кинозал, в котором будет проходить сеанс.
     *
     * @return Кинозал этого сеанса.
     */
    ICinemaHall getCinemaHall();

    /**
     * Установить кинозал для этого сеанса.
     *
     * @param cinemaHall Кинозал для проведения сеанса.
     */
    void setCinemaHall(ICinemaHall cinemaHall);

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
    List<ITicket> getTickets();

    /**
     * Добавить новый билет в список билетов сеанса.
     *
     * @param ticket Новый билет для добавления в сеанс.
     */
    void addTicket(ITicket ticket);

    /**
     * Удалить билет из списка билетов сеанса.
     *
     * @param ticket Билет для удаления из сеанса.
     */
    void removeTicket(ITicket ticket);

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