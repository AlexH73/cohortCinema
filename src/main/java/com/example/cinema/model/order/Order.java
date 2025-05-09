package com.example.cinema.model.order;

import com.example.cinema.model.product.Product;
import com.example.cinema.model.ticket.ITicket;
import com.example.cinema.model.ticket.Ticket;
import com.example.cinema.model.ticket.TicketStatus;
import com.example.cinema.model.user.Customer;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Класс Order представляет заказ, сделанный пользователем (билеты и/или продукты).
 */
@Entity
@Table(name = "orders")
public class Order implements IOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    private Customer user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<Ticket> tickets = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "order_id")
    )
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Product, Integer> products = new HashMap<>();

    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // === Конструктор ===
    public Order(Customer user) {
        this.user = user;
        this.createdAt = LocalDateTime.now();
        this.status = OrderStatus.NEW;
    }

    protected Order() {
        // для JPA
    }

    // === Методы интерфейса и бизнес-логика ===

    @Override
    public List<Ticket> getTickets() {
        return tickets;
    }

    @Override
    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    @Override
    public void removeTicket(Ticket ticket) {
        tickets.remove(ticket);
    }

    @Override
    public Map<Product, Integer> getProducts() {
        return products;
    }

    @Override
    public void addProduct(Product product, int quantity) {
        products.put(product, quantity);
    }

    @Override
    public void removeProduct(Product product) {
        products.remove(product);
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
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            total = total.add(entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())));
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

    // === Геттеры/сеттеры, equals, hashCode, toString ===

    public Long getId() {
        return id;
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
                "id=" + id +
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
        if (!(o instanceof Order order)) return false;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

