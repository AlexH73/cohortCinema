package com.example.cinema.model;

import com.example.cinema.model.product.Product;
import com.example.cinema.repository.IProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        Product product = new Product("Popcorn", new BigDecimal("5.99"), 10);
        when(productRepository.save(product)).thenReturn(product);

        Product saved = productService.save(product);

        assertNotNull(saved);
        assertEquals("Popcorn", saved.getName());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testFindById() {
        Product product = new Product("Cola", new BigDecimal("2.49"), 15);
        product.setId(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product found = productService.findById(1L);

        assertNotNull(found);
        assertEquals("Cola", found.getName());
    }

    @Test
    void testDeleteById() {
        productService.deleteById(2L);

        verify(productRepository, times(1)).deleteById(2L);
    }

    @Test
    void testFindAll() {
        List<Product> products = Arrays.asList(
                new Product("Nachos", new BigDecimal("4.49"), 20),
                new Product("Water", new BigDecimal("1.99"), 30)
        );
        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.findAll();

        assertEquals(2, result.size());
        verify(productRepository, times(1)).findAll();
    }
}

