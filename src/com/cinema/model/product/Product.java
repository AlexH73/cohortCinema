package com.cinema.model.product;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Класс Product представляет товар, который можно приобрести (еда, напитки и т.д.).
 */
@Entity
@Table(name = "product")
public class Product implements IProduct{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CurrencyType currency;

    @Column(nullable = true)
    private int stockQuantity;

    public Product(String name, String description, BigDecimal price, int stockQuantity, CurrencyType currency) {
        validatePrice(price);
        validateStock(stockQuantity);
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.currency = currency;
    }

    // Валидация цены
    private void validatePrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
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
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        validatePrice(price);
        this.price = price;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Описание не может быть пустым.");
        }
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

    @Override
    public CurrencyType getCurrency() {
        return currency;
    }

    @Override
    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }

    // Геттер для ID
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price + currency.getSymbol() +
                ", stock=" + stockQuantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return stockQuantity == product.stockQuantity && id.equals(product.id) && name.equals(product.name) && Objects.equals(description, product.description) && price.equals(product.price) && Objects.equals(currency, product.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, currency, stockQuantity);
    }
}
