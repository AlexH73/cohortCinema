package com.example.cinema.repository.product;

import com.example.cinema.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Интерфейс репозитория продуктов на базе Spring Data JPA.
 * Базовые CRUD-методы уже унаследованы от JpaRepository.
 */
@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

    /**
     * Ищет продукты по части названия без учёта регистра.
     */
    List<Product> findByNameContainingIgnoreCase(String name);

    boolean existsByName(String name);
}
