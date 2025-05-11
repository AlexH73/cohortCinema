package com.example.cinema.app;

import com.example.cinema.controller.order.IOrderController;
import com.example.cinema.controller.product.IProductController;
import com.example.cinema.controller.user.IUserController;
import com.example.cinema.util.utils.ConsoleOutputHandler;

import java.util.Scanner;

public class ConsoleApp {
    private final IProductController productController;
    private final IOrderController   orderController;
    private final IUserController    userController;
    private final ConsoleOutputHandler output;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleApp(IProductController prod, IOrderController ord, IUserController usr, ConsoleOutputHandler out) {
        this.productController = prod;
        this.orderController   = ord;
        this.userController    = usr;
        this.output            = out;
    }

    public void run() {
        int choice;
        do {
            output.printInfo("\n=== Главное меню ===");
            output.printInfo("1. Управление продуктами");
            output.printInfo("2. Управление заказами");
            output.printInfo("3. Управление пользователями");
            output.printInfo("0. Выход");
            output.print("Выберите раздел: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                output.printWarning("❗ Пожалуйста, введите число.");
                choice = -1;
            }
            switch (choice) {
                case 1 -> productController.runProductMenu();
                case 2 -> orderController.runOrderMenu();
                case 3 -> userController.runUserMenu();
                case 0 -> output.printSuccess("Выход из приложения. До свидания!");
                default -> output.printWarning("❗ Неверный пункт меню.");
            }
        } while (choice != 0);
    }
}

