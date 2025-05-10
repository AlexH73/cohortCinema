package com.example.cinema.service.product;

import com.example.cinema.model.product.IProduct;

import java.util.List;

/**
 * Интерфейс IProductService определяет контракт для управления продуктами в системе кинотеатра.
 */
public interface IProductService {

    /**
     * Добавить новый продукт.
     *
     * @param product Продукт для добавления.
     */
    IProduct createProduct(IProduct product);

    /**
     * Удалить продукт по его уникальному идентификатору.
     *
     * @param id Уникальный ID продукта.
     * @return true, если продукт был успешно удалён, иначе false.
     */
    void deleteProduct(Long id);

    /**
     * Получить продукт по его уникальному идентификатору.
     *
     * @param productId Уникальный ID продукта.
     * @return Найденный продукт или null, если не найден.
     */
    IProduct getProductById(Long productId);

    /**
     * Получить список всех продуктов.
     *
     * @return Список всех доступных продуктов.
     */
    List<IProduct> getAllProducts();

    IProduct updateProduct(Long id, IProduct product);
}
