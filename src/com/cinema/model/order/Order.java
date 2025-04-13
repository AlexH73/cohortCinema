package com.cinema.model.order;

import com.cinema.model.product.Product;
import com.cinema.model.ticket.Ticket;
import com.cinema.model.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Класс Order представляет заказ, сделанный пользователем (билеты и/или продукты).
 */
public class Order {
    private final String id;
    private User user;
    private List<Ticket> tickets;
    private List<Product> products;
    private final LocalDateTime createdAt;
    private boolean isPaid;

    public Order(User user) {
        this.id = UUID.randomUUID().toString();
        this.isPaid = false;
        this.products = new ArrayList<>();
        this.tickets = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public User getUser() {
        return user;
    }

    public void pay() {
        this.isPaid = true;
        // Помечаем все билеты как оплаченные
        for (Ticket ticket : tickets) {
            ticket.setPaid(true);
        }
    }

    public double getTotalPrice() {
        double total = 0;
        for (Ticket ticket : tickets) {
            total += ticket.getSession().getTicketPrice();
        }
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", tickets=" + tickets +
                ", products=" + products +
                ", totalPrice=" + getTotalPrice() +
                ", isPaid=" + isPaid +
                '}';
    }
}
