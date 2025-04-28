package com.cinema.controller.product;

import com.cinema.model.product.Product;
import com.cinema.model.product.IProduct;
import com.cinema.service.product.IProductService;
import com.cinema.util.exceptions.ProductNotFoundException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.Currency;

public class ProductController implements IProductController {
    private final IProductService productService;
    private final ConsoleOutputHandler outputHandler;
    private final Scanner scanner; // Переносим Scanner сюда, в поле класса

    public ProductController(IProductService productService, ConsoleOutputHandler outputHandler) {
        this.productService = productService;
        this.outputHandler = outputHandler;
        this.scanner = new Scanner(System.in); // Инициализация здесь
    }

    public void runProductMenu() {
        int choice;

        do {
            System.out.println("\n=== Меню продуктов ===");
            System.out.println("1. Показать все продукты");
            System.out.println("2. Добавить продукт");
            System.out.println("3. Найти продукт по ID");
            System.out.println("4. Удалить продукт");
            System.out.println("0. Выход");
            System.out.print("Введите ваш выбор: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введите корректное число.");
                choice = -1;
            }

            switch (choice) {
                case 1 -> showAllProducts();
                case 2 -> addProduct();
                case 3 -> findProductById();
                case 4 -> removeProduct();
                case 0 -> System.out.println("Выход из меню продуктов.");
                default -> System.out.println("Неверный выбор. Попробуйте ещё раз.");
            }
        } while (choice != 0);

        scanner.close(); // Закрыть Scanner здесь
    }

    private void addProduct() {
        outputHandler.print("Введите название продукта: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            outputHandler.print("❗ Название не может быть пустым.");
            return;
        }

        outputHandler.print("Введите описание продукта: ");
        String description = scanner.nextLine().trim();

        outputHandler.print("Введите цену продукта: ");
        double price;
        try {
            price = Double.parseDouble(scanner.nextLine());
            if (price <= 0) {
                outputHandler.print("❗ Цена должна быть положительной.");
                return;
            }
        } catch (NumberFormatException e) {
            outputHandler.print("❗ Неверный формат цены.");
            return;
        }

        outputHandler.print("Введите количество на складе: ");
        int stockQuantity;
        try {
            stockQuantity = Integer.parseInt(scanner.nextLine());
            if (stockQuantity < 0) {
                outputHandler.print("❗ Количество не может быть отрицательным.");
                return;
            }
        } catch (NumberFormatException e) {
            outputHandler.print("❗ Неверный формат количества.");
            return;
        }

        BigDecimal priceBigDecimal = BigDecimal.valueOf(price);
        Currency currency = Currency.getInstance("USD");

        IProduct product = new Product(
                name,
                description,
                priceBigDecimal,
                stockQuantity,
                currency
        );

        productService.createProduct(product);
        outputHandler.print("✅ Продукт добавлен: " + product);
    }

    private void removeProduct() {
        outputHandler.print("Введите ID продукта для удаления: ");
        String idInput = scanner.nextLine().trim();

        try {
            Long id = Long.parseLong(idInput);
            productService.deleteProduct(id);
            outputHandler.print("✅ Продукт удалён.");
        } catch (NumberFormatException e) {
            outputHandler.print("❗ Неверный формат ID.");
        } catch (ProductNotFoundException e) {
            outputHandler.print("❌ Ошибка: " + e.getMessage());
        }
    }

    private void showAllProducts() {
        try {
            List<IProduct> products = productService.getAllProducts();
            if (products.isEmpty()) {
                outputHandler.print("🔍 Список продуктов пуст.");
            } else {
                outputHandler.print("📦 Все продукты:");
                products.forEach(p -> outputHandler.print(p.toString()));
            }
        } catch (Exception e) {
            outputHandler.print("❌ Ошибка при загрузке данных: " + e.getMessage());
        }
    }

    private void findProductById() {
        outputHandler.print("Введите ID продукта: ");
        String idInput = scanner.nextLine().trim();

        try {
            Long id = Long.parseLong(idInput);
            IProduct product = productService.getProductById(id);
            outputHandler.print("🔍 Найден продукт: " + product);
        } catch (NumberFormatException e) {
            outputHandler.print("❗ Неверный формат ID.");
        } catch (ProductNotFoundException e) {
            outputHandler.print("❌ Ошибка: " + e.getMessage());
        }
    }
}
