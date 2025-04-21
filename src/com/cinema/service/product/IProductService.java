package com.cinema.service.product;

import com.cinema.model.product.IProduct;
import com.cinema.model.product.Product;
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
    void addProduct(Product product);

    /**
     * Удалить продукт по его уникальному идентификатору.
     *
     * @param productId Уникальный ID продукта.
     * @return true, если продукт был успешно удалён, иначе false.
     */
    boolean removeProductById(String productId);

    /**
     * Получить продукт по его уникальному идентификатору.
     *
     * @param productId Уникальный ID продукта.
     * @return Найденный продукт или null, если не найден.
     */
    Product getProductById(String productId);

    /**
     * Получить список всех продуктов.
     *
     * @return Список всех доступных продуктов.
     */
    List<IProduct> getAllProducts();

    void createProduct(IProduct product);
}
