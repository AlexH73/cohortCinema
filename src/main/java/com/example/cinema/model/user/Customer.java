package com.example.cinema.model.user;

import com.example.cinema.model.hall.Seat;
import com.example.cinema.model.order.Order;
import com.example.cinema.model.product.Product;
import com.example.cinema.model.session.Session;
import com.example.cinema.model.ticket.Ticket;
import com.example.cinema.util.exceptions.BookingException;
import com.example.cinema.util.exceptions.InsufficientStockException;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("CUSTOMER")
public class Customer extends AbstractUser implements ICustomer {
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orderHistory = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> bookingHistory = new ArrayList<>();

    public Customer(String username, String password) {
        super();
        this.setUserLogin(username);
        this.setPassword(password);
        this.setRole(Role.CLIENT);
    }

    // Реализация метода bookTicket из ICustomer
    @Override
    public Ticket bookTicket(Session session, Seat seat) throws BookingException {
        if (session == null) {
            throw new IllegalArgumentException("Сеанс не может быть null.");
        }

        if (session.getAvailableSeats() <= 0) {
            throw new BookingException("На данный момент нет свободных мест на сеанс.");
        }

        // Пример: создание билета и добавление его в историю бронирований
        Ticket ticket = new Ticket(session, this, seat, session.getTicketPrice());
        synchronized (bookingHistory) {
            bookingHistory.add(ticket);
        }
        session.setAvailableSeats(session.getAvailableSeats() - 1);
        return ticket;
    }

    // Реализация метода purchaseProduct из ICustomer
    @Override
    public Order purchaseProduct(Product product, int quantity) throws InsufficientStockException {
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
        Order order = new Order(this);
        ((Order) order).addProduct(product, quantity);

        synchronized (orderHistory) {
            orderHistory.add(order);
        }

        product.reduceStock(quantity);
        return order;
    }

    // Реализация метода getOrderHistory из ICustomer
    @Override
    public List<Order> getOrderHistory() {
        synchronized (orderHistory) {
            return new ArrayList<>(orderHistory);
        }
    }

    // Реализация метода getBookingHistory из ICustomer
    @Override
    public List<Ticket> getBookingHistory() {
        synchronized (bookingHistory) {
            return new ArrayList<>(bookingHistory);
        }
    }

    @Override
    public String getUsername() {
        return  getUserLogin();
    }

    @Override
    public void setUsername(String userLogin) {
        setUserLogin(userLogin);
    }

    @Override
    public String getPasswordHash() {
        return getPassword();
    }

    @Override
    public void setPasswordHash(String passwordHash) {
        setPassword(passwordHash);
    }

    @Override
    public String getPasswordSalt() {
        return "";
    }

    @Override
    public void setPasswordSalt(String passwordSalt) {

    }
}