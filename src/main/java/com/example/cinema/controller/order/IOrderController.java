package com.example.cinema.controller.order;

public interface IOrderController {
    void createOrder(); // позже можно расширить параметрами
    void showAllOrders();
    void showOrdersByCustomer();
}
