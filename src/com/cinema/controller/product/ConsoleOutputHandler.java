package com.cinema.controller.product;

/**
 * Класс ConsoleOutputHandler реализует интерфейс OutputHandler и выводит сообщения в консоль.
 */
public class ConsoleOutputHandler {
    /**
     * Выводит сообщение в консоль.
     *
     * @param message Сообщение для вывода.
     */
    public void print(String message) {
        System.out.println(message);
    }
}