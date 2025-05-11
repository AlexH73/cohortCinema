package com.example.cinema.service.order;

import com.example.cinema.model.order.Order;
import com.example.cinema.model.order.OrderStatus;
import com.example.cinema.model.ticket.Ticket;
import com.example.cinema.model.product.Product;
import com.example.cinema.model.ticket.TicketStatus;
import com.example.cinema.model.user.Customer;
import com.example.cinema.repository.order.IOrderRepository;
import com.example.cinema.repository.ticket.ITicketRepository;
import com.example.cinema.repository.product.IProductRepository;
import com.example.cinema.util.exceptions.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements IOrderService {
    private final IOrderRepository orderRepository;
    private final ITicketRepository ticketRepository;
    private final IProductRepository productRepository;

    public OrderServiceImpl(IOrderRepository orderRepository,
                            ITicketRepository ticketRepository,
                            IProductRepository productRepository) {
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

        if (ticket.getStatus() != TicketStatus.AVAILABLE) {
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

        order.addProduct(product, quantity);
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
        order.getProducts().forEach((product, quantity) -> {
            product.increaseStock(quantity);
            productRepository.save(product);
        });

        order.setStatus(OrderStatus.CANCELLED);
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> getOrderById(Long orderId) {
        return Optional.ofNullable(orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId)));
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
