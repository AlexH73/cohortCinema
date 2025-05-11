package com.example.cinema.model;

import com.example.cinema.model.order.*;
import com.example.cinema.model.product.CurrencyType;
import com.example.cinema.model.product.Product;
import com.example.cinema.model.ticket.Ticket;
import com.example.cinema.model.user.Customer;
import com.example.cinema.repository.order.IOrderRepository;
import com.example.cinema.repository.product.IProductRepository;
import com.example.cinema.repository.ticket.ITicketRepository;
import com.example.cinema.service.order.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {

    private IOrderRepository orderRepository;
    private ITicketRepository ticketRepository;
    private IProductRepository productRepository;
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        orderRepository = mock(IOrderRepository.class);
        ticketRepository = mock(ITicketRepository.class);
        productRepository = mock(IProductRepository.class);
        orderService = new OrderServiceImpl(orderRepository, ticketRepository, productRepository);
    }

    @Test
    void testCreateOrder() {
        Customer customer = new Customer("user", "pass");
        Order order = new Order(customer);
        when(orderRepository.save(order)).thenReturn(order);

        Order created = orderService.createOrder(order.getCustomer());

        assertNotNull(created);
        assertEquals(OrderStatus.NEW, created.getStatus());
        verify(orderRepository).save(order);
    }

    @Test
    void testGetAllOrders() {
        List<Order> mockOrders = List.of(new Order(new Customer("a", "b")));
        when(orderRepository.findAll()).thenReturn(mockOrders);

        List<Order> result = orderService.getAllOrders();

        assertEquals(1, result.size());
    }

    @Test
    void testDeleteOrder() {  // Проверка исключения
        orderService.cancelOrder(1L);
        verify(orderRepository).deleteById(1L);
    }

    @Test
    void testCancelOrder() {
        // Arrange
        Customer customer = new Customer("john_doe", "securepass");
        Order order = new Order(customer);
        Product product = new Product("Cola", "Cold", new BigDecimal("1.99"), 10, CurrencyType.EUR);
        order.addProduct(product, 2);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        // Act
        orderService.cancelOrder(1L);

        // Assert
        assertEquals(OrderStatus.CANCELLED, order.getStatus());
        verify(orderRepository, times(1)).save(order);
        verify(productRepository, times(1)).save(product);
    }


    @Test
    void testFindOrderById() {
        Order order = new Order(new Customer("c", "d"));
        when(orderRepository.findById(5L)).thenReturn(Optional.of(order));

        Order result = orderService.getOrderById(5L);

        assertNotNull(result);
        assertEquals(order, result);
    }
}
