package com.example.cinema.controller.product;

/**
 * Класс ConsoleOutputHandler реализует интерфейс OutputHandler и выводит сообщения в консоль с поддержкой цветов.
 */
public class ConsoleOutputHandler {

    // ANSI escape codes для цветов
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";

    /**
     * Выводит обычное сообщение без цвета.
     *
     * @param message Сообщение для вывода.
     */
    public void print(String message) {
        System.out.println(message);
    }

    /**
     * Выводит сообщение об успешной операции зелёным цветом.
     *
     * @param message Сообщение об успехе.
     */
    public void printSuccess(String message) {
        System.out.println(GREEN + message + RESET);
    }

    /**
     * Выводит сообщение об ошибке красным цветом.
     *
     * @param message Сообщение об ошибке.
     */
    public void printError(String message) {
        System.out.println(RED + message + RESET);
    }

    /**
     * Выводит предупреждающее сообщение жёлтым цветом.
     *
     * @param message Сообщение-предупреждение.
     */
    public void printWarning(String message) {
        System.out.println(YELLOW + message + RESET);
    }

    /**
     * Выводит информационное сообщение синим цветом.
     *
     * @param message Информационное сообщение.
     */
    public void printInfo(String message) {
        System.out.println(BLUE + message + RESET);
    }
}
