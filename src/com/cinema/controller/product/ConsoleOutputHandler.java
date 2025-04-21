package com.cinema.controller.product;

/**
 * Класс ConsoleOutputHandler реализует интерфейс OutputHandler и выводит сообщения в консоль.
 */
class ConsoleOutputHandler implements OutputHandler {
    /**
     * Выводит сообщение в консоль.
     *
     * @param message Сообщение для вывода.
     */
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}