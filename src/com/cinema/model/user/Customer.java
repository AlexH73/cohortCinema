package com.cinema.model.user;

import com.cinema.model.order.IOrder;
import com.cinema.model.order.Order;
import com.cinema.model.product.IProduct;
import com.cinema.model.session.ISession;
import com.cinema.model.ticket.ITicket;
import com.cinema.model.ticket.Ticket;

import java.util.ArrayList;
import java.util.List;

public class Customer extends AbstractUser implements ICustomer {
    private List<IOrder> orderHistory = new ArrayList<>();
    private List<ITicket> bookingHistory = new ArrayList<>();

    public Customer(String username, String password) {
        super(username, password, UserRole.CLIENT);
    }

    // Реализация метода bookTicket из ICustomer
    @Override
    public ITicket bookTicket(ISession session) {
        // Пример: создание билета и добавление его в историю бронирований
        ITicket ticket = new Ticket(session, this, 1, 1, session.getTicketPrice());
        bookingHistory.add(ticket);
        return ticket;
    }

    // Реализация метода purchaseProduct из ICustomer
    @Override
    public IOrder purchaseProduct(IProduct product, int quantity) {
        // Пример: создание заказа и добавление продукта
        IOrder order = new Order(this);
        ((Order) order).addProduct(product);
        orderHistory.add(order);
        return order;
    }

    // Реализация метода getOrderHistory из ICustomer
    @Override
    public List<IOrder> getOrderHistory() {
        return new ArrayList<>(orderHistory);
    }

    // Реализация метода getBookingHistory из ICustomer
    @Override
    public List<ITicket> getBookingHistory() {
        return new ArrayList<>(bookingHistory);
    }
}