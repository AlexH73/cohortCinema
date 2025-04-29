package com.cinema.service.order;

import com.cinema.model.order.Order;
import com.cinema.model.order.OrderStatus;
import com.cinema.model.ticket.Ticket;
import com.cinema.model.product.Product;
import com.cinema.model.user.Customer;
import com.cinema.repository.order.OrderRepository;
import com.cinema.repository.ticket.TicketRepository;
import com.cinema.repository.product.ProductRepository;
import com.cinema.util.exceptions.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {
    private final OrderRepository orderRepository;
    private final TicketRepository ticketRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            TicketRepository ticketRepository,
                            ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.ticketRepository = ticketRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Order createOrder(Customer user) {
        if (user == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        Order order = new Order(user);
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order addTicket(Long orderId, Long ticketId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        if (order.getStatus() != OrderStatus.NEW) {
            throw new InvalidOrderStateException("Cannot modify order in state " + order.getStatus());
        }

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException(ticketId));

        if (!ticket.getStatus().equalsIgnoreCase("AVAILABLE")) {
            throw new TicketNotAvailableException(ticketId);
        }

        order.addTicket(ticket);
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order addProduct(Long orderId, Long productId, int quantity) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        if (order.getStatus() != OrderStatus.NEW) {
            throw new InvalidOrderStateException("Cannot modify order in state " + order.getStatus());
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        if (product.getStockQuantity() < quantity) {
            throw new ProductNotAvailableException(productId);
        }

        // уменьшаем складской остаток
        product.reduceStock(quantity);
        productRepository.save(product);

        order.addProduct(product);
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order payOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        if (order.getStatus() != OrderStatus.NEW) {
            throw new InvalidOrderStateException("Only NEW orders can be paid");
        }
        if (order.getTickets().isEmpty() && order.getProducts().isEmpty()) {
            throw new InvalidOrderStateException("Cannot pay an empty order");
        }

        order.pay(); // меняет статус и помечает билеты как SOLD
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        if (order.getStatus() != OrderStatus.NEW) {
            throw new InvalidOrderStateException("Only NEW orders can be cancelled");
        }

        // вернуть продукты на склад
        order.getProducts().forEach(p -> {
            p.increaseStock(1); // или нужное количество
            productRepository.save(p);
        });

        order.setStatus(OrderStatus.CANCELLED);
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
