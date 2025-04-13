package com.cinema.repository.product;

import com.cinema.model.product.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Реализация интерфейса IProductRepository с хранением данных в памяти (список).
 */
public class ProductRepository implements IProductRepository {

    private final List<Product> productList = new ArrayList<>();

    @Override
    public void save(Product product) {
        productList.add(product);
    }

    @Override
    public boolean deleteById(String id) {
        return productList.removeIf(product -> product.getId().equals(id));
    }

    @Override
    public Product findById(String id) {
        for (Product product : productList) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productList); // Возвращаем копию, чтобы защитить исходный список
    }

    @Override
    public boolean update(Product updatedProduct) {
        for (int i = 0; i < productList.size(); i++) {
            Product current = productList.get(i);
            if (current.getId().equals(updatedProduct.getId())) {
                productList.set(i, updatedProduct);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> result = new ArrayList<>();
        for (Product product : productList) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }

    @Override
    public void clear() {
        productList.clear();
    }
}
