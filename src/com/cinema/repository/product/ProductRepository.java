package com.cinema.repository.product;

import com.cinema.model.product.IProduct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Класс ProductRepository реализует интерфейс IProductRepository и предоставляет
 * методы для управления продуктами в памяти.
 */
public class ProductRepository implements IProductRepository {

    // Хранилище продуктов (имитация базы данных)
    private final Map<String, IProduct> products = new HashMap<>();

    @Override
    public IProduct save(IProduct product) {
        products.put(product.getId(), product);
        return product;
    }

    @Override
    public boolean deleteById(Long productId) {
        return products.remove(productId) != null;
    }

    @Override
    public Optional<IProduct> findById(Long productId) {
        return Optional.ofNullable(products.get(productId));
    }

    @Override
    public List<IProduct> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public boolean update(IProduct updatedProduct) {
        if (products.containsKey(updatedProduct.getId())) {
            products.put(updatedProduct.getId(), updatedProduct);
            return true;
        }
        return false;
    }

    @Override
    public List<IProduct> findByName(String name) {
        List<IProduct> result = new ArrayList<>();
        for (IProduct product : products.values()) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }


    @Override
    public void clear() {
        products.clear();
    }
}