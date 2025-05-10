package com.example.cinema.service.product;

import com.example.cinema.model.product.IProduct;
import com.example.cinema.model.product.Product;
import com.example.cinema.repository.product.IProductRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Класс ProductService реализует бизнес-логику управления продуктами.
 */
public class ProductService implements IProductService {

    private final IProductRepository productRepository;

    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public IProduct createProduct(IProduct product) {
        if (!(product instanceof Product)) {
            throw new IllegalArgumentException("Объект должен быть экземпляром Product.");
        }
        return productRepository.save((Product) product);
    }

    @Override
    public IProduct getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("❌ Продукт с ID " + id + " не найден."));
    }

    @Override
    public List<IProduct> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(p -> (IProduct) p) // Явное приведение для соблюдения интерфейса
                .collect(Collectors.toList());
    }

    @Override
    public IProduct updateProduct(Long id, IProduct updatedData) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("❌ Продукт с ID " + id + " не найден."));

        existing.setName(updatedData.getName());
        existing.setDescription(updatedData.getDescription());
        existing.setPrice(updatedData.getPrice());
        existing.setStockQuantity(updatedData.getStockQuantity());
        existing.setCurrency(updatedData.getCurrency());

        return productRepository.save(existing);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new NoSuchElementException("❌ Нельзя удалить: продукт с ID " + id + " не существует.");
        }
        productRepository.deleteById(id);
    }
}
