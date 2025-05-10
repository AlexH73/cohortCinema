package com.example.cinema.controller.product;

/**
 * Интерфейс IProductController определяет контракт для классов,
 * управляющих операциями с продуктами (добавление, удаление, просмотр и т.д.).
 */
public interface IProductController {

    /**
     * Запускает меню управления продуктами, предоставляя пользователю
     * возможность выбора различных операций.
     */
    void runProductMenu();
}