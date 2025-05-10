package com.example.cinema.model.user;

import com.example.cinema.model.hall.Seat;
import com.example.cinema.model.order.Order;
import com.example.cinema.model.product.Product;
import com.example.cinema.model.session.Session;
import com.example.cinema.model.ticket.Ticket;
import com.example.cinema.util.exceptions.InsufficientStockException;
import com.example.cinema.util.exceptions.BookingException;

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
    Ticket bookTicket(Session session, Seat seat) throws BookingException;

    /**
     * Купить продукт.
     *
     * @param product  Продукт, который следует купить.
     * @param quantity Количество продукта, который следует купить.
     * @return Заказ, содержащий информацию о покупке.
     * @throws InsufficientStockException Если запрошенное количество продукта недоступно на складе.
     */
    Order purchaseProduct(Product product, int quantity) throws InsufficientStockException;

    /**
     * Получить историю всех заказов клиента.
     *
     * @return Список всех заказов клиента.
     */
    List<Order> getOrderHistory();

    /**
     * Получить историю всех бронирований клиента.
     *
     * @return Список всех бронированных клиентом билетов.
     */
    List<Ticket> getBookingHistory();
}