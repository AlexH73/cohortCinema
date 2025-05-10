package com.example.cinema.repository.product;
/*

import com.example.cinema.model.product.IProduct;
import com.example.cinema.model.product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

*/
/**
 * Класс ProductRepository реализует интерфейс IProductRepository и предоставляет
 * методы для управления продуктами в памяти.
 *//*

public class ProductRepository{

    // Хранилище продуктов (имитация базы данных)
    private final Map<Long, IProduct> products = new HashMap<>();

    public IProduct save(IProduct product) {
        products.put(product.getId(), product);
        return product;
    }

    public boolean deleteById(Long productId) {
        return products.remove(productId) != null;
    }

    public Optional<IProduct> findById(Long productId) {
        return Optional.ofNullable(products.get(productId));
    }

    public List<IProduct> findAll() {
        return new ArrayList<>(products.values());
    }

    public boolean update(IProduct updatedProduct) {
        if (products.containsKey(updatedProduct.getId())) {
            products.put(updatedProduct.getId(), updatedProduct);
            return true;
        }
        return false;
    }

    public List<IProduct> findByName(String name) {
        List<IProduct> result = new ArrayList<>();
        for (IProduct product : products.values()) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }


    public void clear() {
        products.clear();
    }

    public boolean existsByName(String name) {
        return true;
    }

    public void delete(Product product) {
    }
}*/
