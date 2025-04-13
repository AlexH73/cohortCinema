package com.cinema.repository.product;

import com.cinema.model.product.Product;
import java.util.List;

public interface IProductRepository {
    void addProduct(Product product);
    Product getProductById(String id);
    List<Product> getAllProducts();
    void updateProduct(Product product);
    void deleteProduct(String id);
}

