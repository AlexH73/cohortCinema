package com.cinema.model.film;

import java.util.UUID;

/**
 * Класс Film представляет фильм, который можно смотреть в кинотеатре.
 */
public class Film {
    private final String id;
    private String title;
    private String description;
    private int durationMinutes;
    private Genre genre;

    public Film(int durationMinutes, String description, Genre genre, String title) {
        this.id = UUID.randomUUID().toString();
        this.durationMinutes = durationMinutes;
        this.description = description;
        this.genre = genre;
        this.title = title;
    }

    public String getId() {
        return id;
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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", genre=" + genre +
                ", durationMinutes=" + durationMinutes + " min" +
                '}';
    }
}
