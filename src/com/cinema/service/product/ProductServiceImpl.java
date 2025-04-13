package com.cinema.service.product;

import com.cinema.model.product.Product;
import com.cinema.repository.product.IProductRepository;
import com.cinema.repository.product.ProductRepository;

import java.util.List;

/**
 * Класс ProductServiceImpl реализует бизнес-логику для управления продуктами.
 */
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;

    public ProductServiceImpl() {
        this.productRepository = new ProductRepository();
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public boolean removeProductById(String productId) {
        return productRepository.deleteById(productId);
    }

    @Override
    public Product getProductById(String productId) {
        return productRepository.findById(productId);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
