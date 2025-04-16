package com.cinema.model.order;

import com.cinema.model.product.IProduct;
import com.cinema.model.ticket.ITicket;
import com.cinema.model.user.Customer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Класс Order представляет заказ, сделанный пользователем (билеты и/или продукты).
 */
public class Order implements IOrder {
    private final String id;
    private Customer user;
    private List<ITicket> tickets;
    private List<IProduct> products;
    private final LocalDateTime createdAt;
    private OrderStatus status; // "NEW", "PAID", "CANCELLED"

    public Order(Customer user) {
        this.id = UUID.randomUUID().toString();
        this.status = OrderStatus.NEW;
        this.products = new ArrayList<>();
        this.tickets = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
        this.user = user;
    }

    // Реализация методов интерфейса IOrder
    @Override
    public List<ITicket> getTickets() {
        return tickets;
    }

    @Override
    public void addTicket(ITicket ticket) {
        tickets.add(ticket);
    }

    @Override
    public void removeTicket(ITicket ticket) {
        tickets.remove(ticket);
    }

    @Override
    public OrderStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public double getTotalPrice() {
        double total = 0;
        for (ITicket ticket : tickets) {
            total += ticket.getPrice();
        }
        for (IProduct product : products) {
            total += product.getPrice();
        }
        return total;
    }

    @Override
    public void pay() {
        if (status != OrderStatus.NEW) {
            throw new IllegalStateException("Заказ уже обработан.");
        }
        this.status = OrderStatus.PAID;
        for (ITicket ticket : tickets) {
            ticket.setStatus("PAID");
        }
    }

    public String getId() {
        return id;
    }


    // Дополнительные методы
    public void addProduct(IProduct product) {
        products.add(product);
    }

    public List<IProduct> getProducts() {
        return products;
    }

    public Customer getUser() {
        return user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", tickets=" + tickets +
                ", products=" + products +
                ", totalPrice=" + getTotalPrice() +
                ", status=" + status +
                '}';
    }
}
