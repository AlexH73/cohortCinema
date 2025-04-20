package com.cinema.model.user;

import com.cinema.model.hall.Seat;
import com.cinema.model.order.IOrder;
import com.cinema.model.order.Order;
import com.cinema.model.product.IProduct;
import com.cinema.model.session.ISession;
import com.cinema.model.ticket.ITicket;
import com.cinema.model.ticket.Ticket;
import com.cinema.util.exceptions.BookingException;
import com.cinema.util.exceptions.InsufficientStockException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Customer extends AbstractUser implements ICustomer {
    private final List<IOrder> orderHistory = new ArrayList<>();
    private final List<ITicket> bookingHistory = new ArrayList<>();

    public Customer(String username, String password) {
        super(username, password, UserRole.CLIENT);
    }

    // Реализация метода bookTicket из ICustomer
    @Override
    public ITicket bookTicket(ISession session, Seat seat) throws BookingException {
        if (session == null) {
            throw new IllegalArgumentException("Сеанс не может быть null.");
        }

        if (session.getAvailableSeats() <= 0) {
            throw new BookingException("На данный момент нет свободных мест на сеанс.");
        }

        // Пример: создание билета и добавление его в историю бронирований
        ITicket ticket = new Ticket(session, this, seat, session.getTicketPrice());
        synchronized (bookingHistory) {
            bookingHistory.add(ticket);
        }
        session.setAvailableSeats(session.getAvailableSeats() - 1);
        return ticket;
    }

    // Реализация метода purchaseProduct из ICustomer
    @Override
    public IOrder purchaseProduct(IProduct product, int quantity) throws InsufficientStockException {
        if (product == null) {
            throw new IllegalArgumentException("Продукт не может быть null.");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Количество должно быть положительным.");
        }

        if (product.getStockQuantity() < quantity) {
            throw new InsufficientStockException("Недостаточно товара на складе.");
        }

        // Пример: создание заказа и добавление продукта
        IOrder order = new Order(this);
        ((Order) order).addProduct(product, quantity);

        synchronized (orderHistory) {
            orderHistory.add(order);
        }

        product.reduceStock(quantity);
        return order;
    }

    // Реализация метода getOrderHistory из ICustomer
    @Override
    public List<IOrder> getOrderHistory() {
        synchronized (orderHistory) {
            return new ArrayList<>(orderHistory);
        }
    }

    // Реализация метода getBookingHistory из ICustomer
    @Override
    public List<ITicket> getBookingHistory() {
        synchronized (bookingHistory) {
            return new ArrayList<>(bookingHistory);
        }
    }
}