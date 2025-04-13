package com.cinema.repository.product;

import com.cinema.model.product.Product;

import java.util.List;

/**
 * Интерфейс IProductRepository определяет контракт для доступа и управления данными продуктов в системе кинотеатра.
 */
public interface IProductRepository {

    /**
     * Сохраняет новый продукт или обновляет существующий, если ID совпадает.
     *
     * @param product Продукт для сохранения.
     */
    void save(Product product);

    /**
     * Удаляет продукт по уникальному идентификатору.
     *
     * @param id Уникальный ID продукта.
     * @return true, если продукт успешно удалён; false, если продукт с таким ID не найден.
     */
    boolean deleteById(String id);

    /**
     * Находит продукт по его уникальному идентификатору.
     *
     * @param id Уникальный ID продукта.
     * @return Объект Product, если найден; иначе null.
     */
    Product findById(String id);

    /**
     * Возвращает список всех продуктов в хранилище.
     *
     * @return Список всех доступных продуктов.
     */
    List<Product> findAll();

    /**
     * Обновляет продукт по ID, если он существует.
     *
     * @param updatedProduct Объект продукта с обновлёнными данными.
     * @return true, если продукт был успешно обновлён; false, если продукт не найден.
     */
    boolean update(Product updatedProduct);

    /**
     * Ищет продукты по названию (поиск без учёта регистра, частичное совпадение).
     *
     * @param name Название или часть названия продукта.
     * @return Список продуктов, соответствующих критерию поиска.
     */
    List<Product> findByName(String name);

    /**
     * Очищает все продукты из репозитория (например, при сбросе базы).
     */
    void clear();
}
