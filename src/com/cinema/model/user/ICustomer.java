package com.cinema.model.user;

import com.cinema.model.hall.Seat;
import com.cinema.model.order.IOrder;
import com.cinema.model.product.IProduct;
import com.cinema.model.session.ISession;
import com.cinema.model.ticket.ITicket;
import com.cinema.util.exceptions.InsufficientStockException;
import com.cinema.util.exceptions.BookingException;

import java.util.List;

/**
 * Интерфейс ICustomer предоставляет контракт для класса, представляющего клиента в системе управления кинотеатром.
 */
public interface ICustomer extends IUser {

    /**
     * Забронировать билет на указанный сеанс.
     *
     * @param session Сеанс, на который следует забронировать билет.
     * @param seat    Место для бронирования.
     * @return Забронированный билет.
     * @throws BookingException Если билеты на сеанс закончились или сеанс уже начался.
     */
    ITicket bookTicket(ISession session, Seat seat) throws BookingException;

    /**
     * Купить продукт.
     *
     * @param product  Продукт, который следует купить.
     * @param quantity Количество продукта, который следует купить.
     * @return Заказ, содержащий информацию о покупке.
     * @throws InsufficientStockException Если запрошенное количество продукта недоступно на складе.
     */
    IOrder purchaseProduct(IProduct product, int quantity) throws InsufficientStockException;

    /**
     * Получить историю всех заказов клиента.
     *
     * @return Список всех заказов клиента.
     */
    List<IOrder> getOrderHistory();

    /**
     * Получить историю всех бронирований клиента.
     *
     * @return Список всех бронированных клиентом билетов.
     */
    List<ITicket> getBookingHistory();
}