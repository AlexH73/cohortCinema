package com.cinema.service.product;

import com.cinema.model.product.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Класс ProductService реализует бизнес-логику управления продуктами.
 */
public class ProductService implements IProductService {

    // Временное хранилище продуктов (можно заменить на БД в будущем)
    private final List<Product> products = new ArrayList<>();

    @Override
    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public boolean removeProductById(String productId) {
        return products.removeIf(product -> product.getId().equals(productId));
    }

    @Override
    public Product getProductById(String productId) {
        Optional<Product> found = products.stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst();
        return found.orElse(null); // можно бросать исключение, если нужно
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }
}
