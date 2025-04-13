package com.cinema.model.product;

import java.util.UUID;

/**
 * Класс Product представляет товар, который можно приобрести (еда, напитки и т.д.).
 */
public class Product {
    private final String id;
    private String name;
    private String description;
    private double price;

    public Product(String description, String name, double price) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.name = name;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
