package com.example.cinema.model.user;

import com.example.cinema.model.film.Film;
import com.example.cinema.model.film.Genre;
import com.example.cinema.model.hall.Hall;
import com.example.cinema.model.product.CurrencyType;
import com.example.cinema.model.product.IProduct;
import com.example.cinema.model.product.Product;
import com.example.cinema.model.session.ISession;
import com.example.cinema.service.report.IReportGenerator;
import com.example.cinema.util.exceptions.FilmCreationException;
import com.example.cinema.util.exceptions.FilmDeletionException;
import com.example.cinema.util.exceptions.SessionCreationException;
import com.example.cinema.util.exceptions.SessionDeletionException;
import com.example.cinema.util.exceptions.ProductCreationException;
import com.example.cinema.util.exceptions.ProductDeletionException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Интерфейс IAdministrator предоставляет контракт для класса, представляющего администратора в системе управления кинотеатром.
 */
public interface IAdministrator {

    /**
     * Создать новый фильм в системе.
     *
     * @param title     Название фильма.
     * @param durationMinutes Продолжительность фильма.
     * @return Информация о созданном фильме.
     * @throws FilmCreationException Если не удалось создать фильм.
     */
    Film createFilm(String title, String description, int durationMinutes, Genre genre,
                     double rating, String language, String posterUrl, LocalDate releaseDate) throws FilmCreationException;

    /**
     * Создать новый сеанс в системе.
     *
     * @param film      Фильм для сеанса.
     * @param cinemaHall      Кинозал для сеанса.
     * @param startTime Дата и время сеанса.
     * @return Информация о созданном сеансе.
     * @throws SessionCreationException Если не удалось создать сеанс.
     */
    ISession createSession(Film film, Hall cinemaHall, LocalDateTime startTime, double ticketPrice) throws SessionCreationException;

    /**
     * Создать новый продукт в системе.
     *
     * @param name          Название продукта.
     * @param price         Цена продукта.
     * @param description   Описание продукта.
     * @param stockQuantity Количество продукта на складе.
     * @param currency      Валюта продукта.
     * @return Информация о созданном продукте.
     * @throws ProductCreationException Если не удалось создать продукт.
     */
    Product createProduct(String name, String description, BigDecimal price, int stockQuantity, CurrencyType currency) throws ProductCreationException;

    /**
     * Удалить фильм из системы.
     *
     * @param film Фильм для удаления.
     * @throws FilmDeletionException Если не удалось удалить фильм.
     */
    void deleteFilm(Film film) throws FilmDeletionException;

    /**
     * Удалить сеанс из системы.
     *
     * @param session Сеанс для удаления.
     * @throws SessionDeletionException Если не удалось удалить сеанс.
     */
    void deleteSession(ISession session) throws SessionDeletionException;

    /**
     * Удалить продукт из системы.
     *
     * @param product Продукт для удаления.
     * @throws ProductDeletionException Если не удалось удалить продукт.
     */
    void deleteProduct(IProduct product) throws ProductDeletionException;

    /**
     * Получить генератор отчетов.
     *
     * @return Генератор отчетов.
     */
    IReportGenerator getReportGenerator();
}