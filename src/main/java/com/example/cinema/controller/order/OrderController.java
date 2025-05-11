package com.example.cinema.controller.order;

import com.example.cinema.model.order.Order;
import com.example.cinema.model.order.OrderStatus;
import com.example.cinema.service.order.IOrderService;
import com.example.cinema.util.exceptions.OrderNotFoundException;
import com.example.cinema.util.utils.ConsoleOutputHandler;

import java.util.List;
import java.util.Scanner;

public class OrderController implements IOrderController {
    private final IOrderService orderService;
    private final ConsoleOutputHandler output;
    private final Scanner scanner;

    public OrderController(IOrderService orderService, ConsoleOutputHandler output) {
        this.orderService = orderService;
        this.output = output;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void runOrderMenu() {
        int choice;
        do {
            output.printInfo("\n=== Меню заказов ===");
            output.printInfo("1. Показать все заказы");
            output.printInfo("2. Найти заказ по ID");
            output.printInfo("3. Оплатить заказ");
            output.printInfo("4. Отменить заказ");
            output.printInfo("0. Выход");
            output.print("Выберите действие: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ex) {
                output.printWarning("❗ Введите корректное число.");
                choice = -1;
            }

            switch (choice) {
                case 1 -> showAllOrders();
                case 2 -> findOrderById();
                case 3 -> payOrder();
                case 4 -> cancelOrder();
                case 0 -> output.printSuccess("Выход из меню заказов.");
                default -> output.printWarning("❗ Неверный выбор.");
            }
        } while (choice != 0);
    }

    private void showAllOrders() {
        List<Order> all = orderService.getAllOrders();
        if (all.isEmpty()) {
            output.printInfo("🔍 Заказы не найдены.");
        } else {
            output.printInfo("📦 Список всех заказов:");
            all.forEach(o -> output.print(o.toString()));
        }
    }

    private void findOrderById() {
        output.print("Введите ID заказа: ");
        try {
            long id = Long.parseLong(scanner.nextLine());
            Order order = orderService.getOrderById(id)
                    .orElseThrow(() -> new OrderNotFoundException(id));
            output.printInfo("🔍 Заказ найден: " + order);
        } catch (NumberFormatException ex) {
            output.printError("❗ Неверный формат ID.");
        } catch (OrderNotFoundException ex) {
            output.printError("❌ " + ex.getMessage());
        }
    }

    private void payOrder() {
        output.print("Введите ID заказа для оплаты: ");
        try {
            long id = Long.parseLong(scanner.nextLine());
            orderService.payOrder(id);
            output.printSuccess("✅ Заказ #" + id + " оплачен.");
        } catch (NumberFormatException ex) {
            output.printError("❗ Неверный формат ID.");
        } catch (OrderNotFoundException ex) {
            output.printError("❌ " + ex.getMessage());
        } catch (IllegalStateException ex) {
            output.printError("❌ " + ex.getMessage());
        }
    }

    private void cancelOrder() {
        output.print("Введите ID заказа для отмены: ");
        try {
            long id = Long.parseLong(scanner.nextLine());
            orderService.cancelOrder(id);
            output.printSuccess("✅ Заказ #" + id + " отменён.");
        } catch (NumberFormatException ex) {
            output.printError("❗ Неверный формат ID.");
        } catch (OrderNotFoundException ex) {
            output.printError("❌ " + ex.getMessage());
        } catch (IllegalStateException ex) {
            output.printError("❌ " + ex.getMessage());
        }
    }
}
