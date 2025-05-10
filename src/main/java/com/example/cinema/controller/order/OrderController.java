package com.example.cinema.controller.order;

import com.example.cinema.service.order.IOrderService;
import com.example.cinema.util.utils.ConsoleOutputHandler;

public class OrderController implements IOrderController {

    private final IOrderService orderService;
    private final ConsoleOutputHandler console;

    public OrderController(IOrderService orderService, ConsoleOutputHandler console) {
        this.orderService = orderService;
        this.console = console;
    }

    @Override
    public void createOrder() {
        // TODO: реализовать логику оформления заказа
        console.printInfo("Функция оформления заказа пока не реализована.");
    }

    @Override
    public void showAllOrders() {
        //orderService.getOrdersByUser(Long userId).forEach(order -> console.print(order.toString()));
    }

    @Override
    public void showOrdersByCustomer() {
        // TODO: реализовать выбор по клиенту
        console.printWarning("Фильтрация заказов по клиенту пока не реализована.");
    }
}