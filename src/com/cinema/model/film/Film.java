package com.cinema.model.film;

import java.util.UUID;

/**
 * Класс Film представляет фильм, который можно смотреть в кинотеатре.
 */
public class Film implements IFilm {
    private final String id;
    private String title;
    private String description;
    private int durationMinutes;
    private Genre genre;
    private String rating;
    private String language;

    public Film(String title, String description, int durationMinutes, Genre genre) {
        validate(durationMinutes);
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.durationMinutes = durationMinutes;
        this.genre = genre;
    }

    private void validate(int duration) {
        if (duration <= 0) {
            throw new IllegalArgumentException("Длительность должна быть > 0.");
        }
    }

    public String getName() {
        return title;
    }

    public void setName(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return durationMinutes;
    }

    public void setDuration(int duration) {
        this.durationMinutes = duration;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String getRating() {
        return rating;
    }

    @Override
    public void setRating(String rating) {
        if (rating == null || rating.isBlank()) {
            throw new IllegalArgumentException("Рейтинг не может быть пустым.");
        }
        this.rating = rating;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getLanguage() {
        return language;
    }

    @Override
    public void setLanguage(String language) {
        if (language == null || language.isBlank()) {
            throw new IllegalArgumentException("Язык не может быть пустым.");
        }
        this.language = language;
    }

    public String getId() {
        return id;
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
