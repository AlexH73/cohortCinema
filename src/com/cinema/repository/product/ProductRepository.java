package com.cinema.repository.product;

import com.cinema.model.product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс ProductRepository реализует интерфейс IProductRepository и предоставляет
 * методы для управления продуктами в памяти.
 */
public class ProductRepository implements IProductRepository {

    // Хранилище продуктов (имитация базы данных)
    private final Map<String, Product> products = new HashMap<>();

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public boolean deleteById(String productId) {
        return products.remove(productId) != null;
    }

    @Override
    public Product findById(String productId) {
        return products.get(productId);
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void clear() {
        products.clear();
    }

    @Override
    public boolean update(Product updatedProduct) {
        if (products.containsKey(updatedProduct.getId())) {
            products.put(updatedProduct.getId(), updatedProduct);
            return true;
        }
        return false;
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> result = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }

}
