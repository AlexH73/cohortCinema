package com.cinema.service.product;

import com.cinema.model.product.Product;
import com.cinema.repository.product.IProductRepository;

import java.util.List;

/**
 * Класс ProductServiceImpl реализует интерфейс IProductService и управляет бизнес-логикой для продуктов.
 */
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;

    /**
     * Конструктор с внедрением зависимости через интерфейс.
     * @param productRepository Репозиторий продуктов.
     */
    public ProductServiceImpl(IProductRepository productRepository) {
        this.productRepository = productRepository;
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
