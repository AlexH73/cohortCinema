package com.cinema.model.film;

import java.time.LocalDate;

/**
 * Интерфейс IFilm предоставляет контракт для класса, представляющего фильм в системе управления кинотеатром.
 */
public interface IFilm {

    /**
     * Получить название фильма.
     *
     * @return Название фильма.
     */
    String getTitle();

    /**
     * Установить новое название фильма.
     *
     * @param title Новое название фильма.
     */
    void setTitle(String title);

    /**
     * Получить продолжительность фильма в минутах.
     *
     * @return Продолжительность фильма.
     */
    int getDurationMinutes();

    /**
     * Установить новую продолжительность фильма.
     *
     * @param durationMinutes Продолжительность фильма в минутах.
     */
    void setDurationMinutes(int durationMinutes);

    /**
     * Получить жанр фильма.
     *
     * @return Жанр фильма.
     */
    Genre getGenre();

    /**
     * Установить новый жанр фильма.
     *
     * @param genre Новый жанр фильма.
     */
    void setGenre(Genre genre);

    /**
     * Получить рейтинг фильма.
     *
     * @return Рейтинг фильма.
     */
    double getRating();

    /**
     * Установить новый рейтинг фильма.
     *
     * @param rating Новый рейтинг фильма.
     */
    void setRating(double rating);

    /**
     * Получить описание фильма.
     *
     * @return Описание фильма.
     */
    String getDescription();

    /**
     * Установить новое описание фильма.
     *
     * @param description Новое описание фильма.
     */
    void setDescription(String description);

    /**
     * Получить язык фильма.
     *
     * @return Язык фильма.
     */
    String getLanguage();

    /**
     * Установить новый язык фильма.
     *
     * @param language Новый язык фильма.
     */
    void setLanguage(String language);

    /**
     * Получить URL постера фильма.
     *
     * @return URL постера фильма.
     */
    String getPosterUrl();

    /**
     * Установить URL постера фильма.
     *
     * @param posterUrl URL постера фильма.
     */
    void setPosterUrl(String posterUrl);

    /**
     * Получить дату выпуска фильма.
     *
     * @return Дата выпуска фильма.
     */
    LocalDate getReleaseDate();

    /**
     * Установить дату выпуска фильма.
     *
     * @param releaseDate Дата выпуска фильма.
     */
    void setReleaseDate(LocalDate releaseDate);

    /**
     * Получить id фильма.
     *
     * @return id фильма.
     */
    Long getId();
}
