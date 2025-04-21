package com.cinema.controller.product;

/**
 * Интерфейс OutputHandler предоставляет контракт для классов, обрабатывающих вывод сообщений.
 * Это позволяет абстрагироваться от конкретного способа вывода (например, консоль, файл, GUI).
 */
public interface OutputHandler {
    /**
     * Выводит сообщение.
     *
     * @param message Сообщение для вывода.
     */
    void print(String message);
}