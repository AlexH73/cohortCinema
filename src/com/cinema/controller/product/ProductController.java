package com.cinema.controller.product;

import com.cinema.model.product.CurrencyType;
import com.cinema.model.product.Product;
import com.cinema.model.product.IProduct;
import com.cinema.service.product.IProductService;
import com.cinema.util.exceptions.ProductNotFoundException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 * Контроллер для управления продуктами через консольное меню.
 */
public class ProductController implements IProductController {
    private final IProductService productService;
    private final ConsoleOutputHandler outputHandler;
    private final Scanner scanner;

    public ProductController(IProductService productService, ConsoleOutputHandler outputHandler) {
        this.productService = productService;
        this.outputHandler = outputHandler;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Запускает основное меню для управления продуктами.
     */
    public void runProductMenu() {
        int choice;

        do {
            outputHandler.printInfo("\n=== Меню продуктов ===");
            outputHandler.printInfo("1. Показать все продукты");
            outputHandler.printInfo("2. Добавить продукт");
            outputHandler.printInfo("3. Найти продукт по ID");
            outputHandler.printInfo("4. Удалить продукт");
            outputHandler.printInfo("0. Выход");
            outputHandler.print("Введите ваш выбор: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                outputHandler.printWarning("❗ Ошибка: Введите корректное число.");
                choice = -1;
            }

            switch (choice) {
                case 1 -> showAllProducts();
                case 2 -> addProduct();
                case 3 -> findProductById();
                case 4 -> removeProduct();
                case 0 -> outputHandler.printSuccess("Выход из меню продуктов.");
                default -> outputHandler.printWarning("❗ Неверный выбор. Попробуйте ещё раз.");
            }
        } while (choice != 0);

        scanner.close();
    }

    private void addProduct() {
        outputHandler.print("Введите название продукта: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            outputHandler.printWarning("❗ Название не может быть пустым.");
            return;
        }

        outputHandler.print("Введите описание продукта: ");
        String description = scanner.nextLine().trim();

        outputHandler.print("Введите цену продукта: ");
        double price;
        try {
            price = Double.parseDouble(scanner.nextLine());
            if (price <= 0) {
                outputHandler.printWarning("❗ Цена должна быть положительной.");
                return;
            }
        } catch (NumberFormatException e) {
            outputHandler.printError("❗ Неверный формат цены.");
            return;
        }

        outputHandler.print("Введите количество на складе: ");
        int stockQuantity;
        try {
            stockQuantity = Integer.parseInt(scanner.nextLine());
            if (stockQuantity < 0) {
                outputHandler.printWarning("❗ Количество не может быть отрицательным.");
                return;
            }
        } catch (NumberFormatException e) {
            outputHandler.printError("❗ Неверный формат количества.");
            return;
        }

        BigDecimal priceBigDecimal = BigDecimal.valueOf(price);

        IProduct product = new Product(
                name,
                description,
                priceBigDecimal,
                stockQuantity,
                CurrencyType.EUR // Пока захардкожено
        );

        productService.createProduct(product);
        outputHandler.printSuccess("✅ Продукт добавлен: " + product);
    }

    private void removeProduct() {
        outputHandler.print("Введите ID продукта для удаления: ");
        String idInput = scanner.nextLine().trim();

        try {
            Long id = Long.parseLong(idInput);
            productService.deleteProduct(id);
            outputHandler.printSuccess("✅ Продукт успешно удалён.");
        } catch (NumberFormatException e) {
            outputHandler.printError("❗ Неверный формат ID.");
        } catch (ProductNotFoundException e) {
            outputHandler.printError("❌ Ошибка: " + e.getMessage());
        }
    }

    private void showAllProducts() {
        try {
            List<IProduct> products = productService.getAllProducts();
            if (products.isEmpty()) {
                outputHandler.printInfo("🔍 Список продуктов пуст.");
            } else {
                outputHandler.printInfo("📦 Список всех продуктов:");
                products.forEach(p -> outputHandler.print(p.toString()));
            }
        } catch (Exception e) {
            outputHandler.printError("❌ Ошибка при загрузке данных: " + e.getMessage());
        }
    }

    private void findProductById() {
        outputHandler.print("Введите ID продукта: ");
        String idInput = scanner.nextLine().trim();

        try {
            Long id = Long.parseLong(idInput);
            IProduct product = productService.getProductById(id);
            outputHandler.printInfo("🔍 Найден продукт: " + product);
        } catch (NumberFormatException e) {
            outputHandler.printError("❗ Неверный формат ID.");
        } catch (ProductNotFoundException e) {
            outputHandler.printError("❌ Ошибка: " + e.getMessage());
        }
    }
}
