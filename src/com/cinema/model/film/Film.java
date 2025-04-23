package com.cinema.model.film;

import java.time.LocalDate;
import java.util.Objects;
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
    private double rating;
    private String language;
    private String posterUrl;
    private LocalDate releaseDate;

    public Film(String title, String description, int durationMinutes, Genre genre,
                double rating, String language, String posterUrl, LocalDate releaseDate) {
        validate(durationMinutes);
        validateRating(rating);
        validateLanguage(language);

        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.durationMinutes = durationMinutes;
        this.genre = genre;
        this.rating = rating;
        this.language = language;
        this.posterUrl = posterUrl;
        this.releaseDate = releaseDate;
    }

    private void validate(int duration) {
        if (duration <= 0) {
            throw new IllegalArgumentException("Длительность должна быть > 0.");
        }
    }

    private void validateRating(double rating) {
        if (rating < 0 || rating > 10) {
            throw new IllegalArgumentException("Рейтинг должен быть в диапазоне от 0 до 10.");
        }
    }


    private void validateLanguage(String language) {
        if (language == null || language.isBlank()) {
            throw new IllegalArgumentException("Язык не может быть пустым.");
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
    public double getRating() {
        return rating;
    }

    @Override
    public void setRating(double rating) {
        validateRating(rating);
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
        validateLanguage(language);
        this.language = language;
    }

    @Override
    public String getPosterUrl() {
        return posterUrl;
    }

    @Override
    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    @Override
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    @Override
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return durationMinutes == film.durationMinutes && Double.compare(film.rating, rating) == 0 && id.equals(film.id) && title.equals(film.title) && Objects.equals(description, film.description) && genre == film.genre && Objects.equals(language, film.language) && Objects.equals(posterUrl, film.posterUrl) && Objects.equals(releaseDate, film.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, durationMinutes, genre, rating, language, posterUrl, releaseDate);
    }
}
