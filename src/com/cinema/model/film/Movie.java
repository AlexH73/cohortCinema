package com.cinema.model.film;

import java.util.UUID;

/**
 * Класс Movie представляет фильм, который можно показывать в кинотеатре.
 * Каждый фильм имеет уникальный идентификатор, название, описание и длительность.
 */
public class Movie {
    private final String id;      // Уникальный идентификатор фильма
    private String title;         // Название фильма
    private String description;   // Краткое описание
    private int durationMinutes;  // Продолжительность в минутах

    /**
     * Конструктор фильма.
     *
     * @param title Название фильма
     * @param description Описание фильма
     * @param durationMinutes Продолжительность в минутах
     */
    public Movie(String title, String description, int durationMinutes) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.durationMinutes = durationMinutes;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    /**
     * Удобный вывод информации о фильме для отладки или логирования.
     */
    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", durationMinutes=" + durationMinutes +
                '}';
    }
}
