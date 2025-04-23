package com.cinema.model.order;

import com.cinema.model.product.IProduct;
import com.cinema.model.ticket.ITicket;
import com.cinema.model.ticket.TicketStatus;
import com.cinema.model.user.Customer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Класс Order представляет заказ, сделанный пользователем (билеты и/или продукты).
 */
public class Order implements IOrder {
    private final String id;
    private Customer user;
    private List<ITicket> tickets;
    private Map<IProduct, Integer> products;
    private final LocalDateTime createdAt;
    private OrderStatus status; // "NEW", "PAID", "CANCELLED"

    public Order(Customer user) {
        this.id = UUID.randomUUID().toString();
        this.status = OrderStatus.NEW;
        this.products = new HashMap<>();
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
    public BigDecimal getTotalPrice() {
        BigDecimal total = BigDecimal.ZERO;
        for (ITicket ticket : tickets) {
            total = total.add(BigDecimal.valueOf(ticket.getPrice()));
        }
        for (Map.Entry<IProduct, Integer> entry : products.entrySet()) {
            BigDecimal productPrice = entry.getKey().getPrice();
            Integer quantity = entry.getValue();
            total = total.add(productPrice.multiply(BigDecimal.valueOf(quantity)));
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
            ticket.setStatus(TicketStatus.SOLD);
        }
    }

    public String getId() {
        return id;
    }

    // Дополнительные методы

    @Override
    public Map<IProduct, Integer> getProducts() {
        return products;
    }

    @Override
    public void addProduct(IProduct product, int quantity) {
        products.put(product, quantity);
    }

    @Override
    public void removeProduct(IProduct product) {
        products.remove(product);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id) && user.equals(order.user) && tickets.equals(order.tickets) && products.equals(order.products) && createdAt.equals(order.createdAt) && status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, tickets, products, createdAt, status);
    }
}
