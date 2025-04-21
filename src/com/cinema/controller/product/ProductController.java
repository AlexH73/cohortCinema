package com.cinema.controller.product;

import com.cinema.model.product.Product;
import com.cinema.model.product.IProduct;
import com.cinema.util.exceptions.ProductNotFoundException;
import com.cinema.service.product.IProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.Currency;

public class ProductController implements IProductController {
    private final IProductService productService;
    private final Scanner scanner;
    private final OutputHandler outputHandler;

    public ProductController(IProductService productService, Scanner scanner, OutputHandler outputHandler) {
        this.productService = productService;
        this.scanner = scanner;
        this.outputHandler = outputHandler;
    }

    public void runProductMenu() {
        boolean exit = false;

        while (!exit) {
            outputHandler.print("\n=== Управление продуктами ===");
            outputHandler.print("1. Добавить продукт");
            outputHandler.print("2. Удалить продукт");
            outputHandler.print("3. Показать все продукты");
            outputHandler.print("4. Найти продукт по ID");
            outputHandler.print("0. Назад");

            outputHandler.print("Выберите действие: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addProduct();
                case "2" -> removeProduct();
                case "3" -> showAllProducts();
                case "4" -> findProductById();
                case "0" -> exit = true;
                default -> outputHandler.print("❗ Неверный ввод. Попробуйте снова.");
            }
        }
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
        Currency currency = Currency.getInstance("USD"); // Валюта по умолчанию
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
        String id = scanner.nextLine().trim();

        try {
            productService.removeProductById(id);
            outputHandler.print("✅ Продукт удалён.");
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
        String id = scanner.nextLine().trim();

        try {
            IProduct product = productService.getProductById(id);
            outputHandler.print("🔍 Найден продукт: " + product);
        } catch (ProductNotFoundException e) {
            outputHandler.print("❌ Ошибка: " + e.getMessage());
        }
    }
}
