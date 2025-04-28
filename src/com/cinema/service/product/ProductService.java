package com.cinema.service.product;

import com.cinema.model.product.IProduct;
import com.cinema.model.product.Product;
import com.cinema.repository.product.IProductRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Класс ProductService реализует бизнес-логику управления продуктами.
 */
public class ProductService implements IProductService {

    private final IProductRepository productRepository; // Репозиторий для доступа к данным продуктов

    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public IProduct createProduct(IProduct product) {
        return productRepository.save(product); // Сохраняем продукт в базу данных
    }

    @Override
    public Product getProductById(Long id) {
        return (Product) productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Продукт с ID " + id + " не найден")); // Если продукт не найден, выбрасываем исключение
    }

    @Override
    public List<IProduct> getAllProducts() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                .collect(Collectors.toList()); // Получаем все продукты из базы данных
    }


    @Override
    public IProduct updateProduct(Long id, IProduct product) {
        IProduct existingProduct = getProductById(id); // Используем getProductById, чтобы обработать случай, когда продукт не найден

        existingProduct.setName(product.getName()); // Обновляем имя
        existingProduct.setDescription(product.getDescription()); // Обновляем описание
        existingProduct.setPrice(product.getPrice()); // Обновляем цену
        existingProduct.setStockQuantity(product.getStockQuantity()); // Обновляем количество на складе

        return productRepository.save(existingProduct); // Сохраняем обновленный продукт в базу данных
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id); // Удаляем продукт из базы данных
    }
}