package com.cinema.model.film;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Класс Film представляет фильм, который можно смотреть в кинотеатре.
 */
@Entity
@Table(name = "films")
public class Film implements IFilm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ✅ Объектный тип Long, чтобы можно было хранить null

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private int durationMinutes;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre;

    @Column(nullable = false)
    private double rating;

    @Column(nullable = false)
    private String language;

    private String posterUrl;

    private LocalDate releaseDate;

    // 🧹 Hibernate требует пустой конструктор (обязательно public или protected)
    protected Film() {}

    public Film(String title, String description, int durationMinutes, Genre genre,
                double rating, String language, String posterUrl, LocalDate releaseDate) {
        setTitle(title);
        setDescription(description);
        setDurationMinutes(durationMinutes);
        setGenre(genre);
        setRating(rating);
        setLanguage(language);
        setPosterUrl(posterUrl);
        setReleaseDate(releaseDate);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Название не может быть пустым.");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description; // Можно быть пустым
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        if (durationMinutes <= 0) {
            throw new IllegalArgumentException("Длительность фильма должна быть положительной.");
        }
        this.durationMinutes = durationMinutes;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        if (genre == null) {
            throw new IllegalArgumentException("Жанр не может быть null.");
        }
        this.genre = genre;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        if (rating < 0 || rating > 10) {
            throw new IllegalArgumentException("Рейтинг должен быть от 0 до 10.");
        }
        this.rating = rating;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        if (language == null || language.isBlank()) {
            throw new IllegalArgumentException("Язык не может быть пустым.");
        }
        this.language = language;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre=" + genre +
                ", durationMinutes=" + durationMinutes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film)) return false;
        Film film = (Film) o;
        return Objects.equals(id, film.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
