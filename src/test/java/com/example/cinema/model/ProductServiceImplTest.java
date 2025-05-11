package com.example.cinema.model;

import com.example.cinema.model.product.*;
import com.example.cinema.repository.product.IProductRepository;
import com.example.cinema.service.product.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    private IProductRepository productRepository;
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        productRepository = mock(IProductRepository.class);
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void testSaveProduct() {
        Product product = new Product("Popcorn", "Соленный", new BigDecimal("5.99"), 10, CurrencyType.EUR);
        when(productRepository.save(product)).thenReturn(product);

        IProduct saved = productService.createProduct(product);

        assertNotNull(saved);
        assertEquals("Popcorn", saved.getName());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testFindById() {
        Product product = new Product("Cola", "Light", new BigDecimal("2.49"), 15, CurrencyType.EUR);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product found = productService.getProductById(1L);

        assertNotNull(found);
        assertEquals("Cola", found.getName());
    }

    @Test
    void testDeleteById() {
        productService.deleteProduct(2L);

        verify(productRepository, times(1)).deleteById(2L);
    }

    @Test
    void testFindAll() {
        List<Product> products = Arrays.asList(
                new Product("Nachos", "Hot", new BigDecimal("4.49"), 20, CurrencyType.USD),
                new Product("Water", "Carbonated", new BigDecimal("1.99"), 30, CurrencyType.RUB)
        );
        when(productRepository.findAll()).thenReturn(products);

        List<IProduct> result = productService.getAllProducts();

        assertEquals(2, result.size());
        verify(productRepository, times(1)).findAll();
    }
}
