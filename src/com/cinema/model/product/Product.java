package com.cinema.model.product;

import java.util.UUID;

/**
 * Класс Product представляет товар, который можно приобрести (еда, напитки и т.д.).
 */
public class Product implements IProduct{
    private final String id;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;

    public Product(String name, String description, double price, int stockQuantity) {
        validatePrice(price);
        validateStock(stockQuantity);
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    // Валидация цены
    private void validatePrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной.");
        }
    }

    // Валидация количества
    private void validateStock(int stockQuantity) {
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("Количество не может быть отрицательным.");
        }
    }

    // Реализация методов интерфейса IProduct
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        validatePrice(price);
        this.price = price;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int getStockQuantity() {
        return stockQuantity;
    }

    @Override
    public void setStockQuantity(int stockQuantity) {
        validateStock(stockQuantity);
        this.stockQuantity = stockQuantity;
    }

    @Override
    public void reduceStock(int quantity) {
        if (quantity > stockQuantity) {
            throw new IllegalStateException("Недостаточно товара на складе.");
        }
        stockQuantity -= quantity;
    }

    @Override
    public void increaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Количество должно быть положительным.");
        }
        stockQuantity += quantity;
    }

    // Геттер для ID
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stockQuantity +
                '}';
    }
}
