package com.example.cinema.model.ticket;


import com.example.cinema.model.hall.Seat;
import com.example.cinema.model.session.Session;

/**
 * Интерфейс ITicket предоставляет контракт для класса, представляющего билет в системе управления кинотеатром.
 */
public interface ITicket {

    /**
     * Получить сеанс, на который предназначен этот билет.
     *
     * @return Сеанс этого билета.
     */
    Session getSession();

    /**
     * Установить сеанс для этого билета.
     *
     * @param session Сеанс для этого билета.
     */
    void setSession(Session session);

    /**
     * Получить номер места, которое занимает этот билет.
     *
     * @return Номер места этого билета.
     */
    Seat getSeat();

    /**
     * Установить номер места для этого билета.
     *
     * @param seat Номер места для этого билета.
     */
    void setSeat(Seat seat);

    /**
     * Получить стоимость этого билета.
     *
     * @return Стоимость билета.
     */
    double getPrice();

    /**
     * Установить стоимость для этого билета.
     *
     * @param price Стоимость этого билета.
     */
    void setPrice(double price);

    /**
     * Получить статус этого билета (продано/не продано).
     *
     * @return Статус этого билета.
     */
    TicketStatus getStatus();

    /**
     * Установить статус для этого билета.
     *
     * @param status Статус для этого билета.
     */
    void setStatus(TicketStatus status);
}
