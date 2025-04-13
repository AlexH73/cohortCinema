package com.cinema.controller.product;

import com.cinema.model.product.Product;
import com.cinema.service.product.IProductService;

import java.util.List;
import java.util.Scanner;

/**
 * Класс ProductController обрабатывает действия пользователя, связанные с продуктами.
 */
public class ProductController {

    private final IProductService productService;
    private final Scanner scanner;

    public ProductController(IProductService productService) {
        this.productService = productService;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Запустить меню управления продуктами.
     */
    public void runProductMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== Управление продуктами ===");
            System.out.println("1. Добавить продукт");
            System.out.println("2. Удалить продукт");
            System.out.println("3. Показать все продукты");
            System.out.println("4. Найти продукт по ID");
            System.out.println("0. Назад");

            System.out.print("Выберите действие: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addProduct();
                case "2" -> removeProduct();
                case "3" -> showAllProducts();
                case "4" -> findProductById();
                case "0" -> exit = true;
                default -> System.out.println("❗ Неверный ввод. Попробуйте снова.");
            }
        }
    }

    private void addProduct() {
        System.out.print("Введите название продукта: ");
        String name = scanner.nextLine();

        System.out.print("Введите описание продукта: ");
        String description = scanner.nextLine();

        System.out.print("Введите цену продукта: ");
        double price;
        try {
            price = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❗ Неверный формат цены.");
            return;
        }

        Product product = new Product(name, description, price);
        productService.addProduct(product);
        System.out.println("✅ Продукт добавлен: " + product);
    }

    private void removeProduct() {
        System.out.print("Введите ID продукта для удаления: ");
        String id = scanner.nextLine();

        boolean removed = productService.removeProductById(id);
        if (removed) {
            System.out.println("✅ Продукт удалён.");
        } else {
            System.out.println("❌ Продукт не найден.");
        }
    }

    private void showAllProducts() {
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("🔍 Список продуктов пуст.");
        } else {
            System.out.println("📦 Все продукты:");
            products.forEach(System.out::println);
        }
    }

    private void findProductById() {
        System.out.print("Введите ID продукта: ");
        String id = scanner.nextLine();

        Product product = productService.getProductById(id);
        if (product != null) {
            System.out.println("🔍 Найден продукт: " + product);
        } else {
            System.out.println("❌ Продукт не найден.");
        }
    }
}

