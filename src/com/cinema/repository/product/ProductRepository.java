package com.cinema.repository.product;

import com.cinema.model.product.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRepository {
    private final List<Product> products = new ArrayList<>();

    @Override
    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public Product getProductById(String id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(products); // возвращаем копию для безопасности
    }

    @Override
    public void updateProduct(Product updateProduct) {
        for (int i = 0; i < products.size(); i++) {
            Product current = products.get(i);
            if (current.getId().equals(updateProduct.getId())) {
                products.set(i, updateProduct);
                return;
            }
        }
    }

    @Override
    public void deleteProduct(String id) {
        products.removeIf(p -> p.getId().equals(id));
    }
}
