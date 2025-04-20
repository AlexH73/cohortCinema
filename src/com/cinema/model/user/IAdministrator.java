package com.cinema.model.user;

import com.cinema.model.film.IFilm;
import com.cinema.model.hall.ICinemaHall;
import com.cinema.model.product.IProduct;
import com.cinema.model.session.ISession;
import com.cinema.service.report.IReportGenerator;
import com.cinema.util.exceptions.FilmCreationException;
import com.cinema.util.exceptions.FilmDeletionException;
import com.cinema.util.exceptions.SessionCreationException;
import com.cinema.util.exceptions.SessionDeletionException;
import com.cinema.util.exceptions.ProductCreationException;
import com.cinema.util.exceptions.ProductDeletionException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

/**
 * Интерфейс IAdministrator предоставляет контракт для класса, представляющего администратора в системе управления кинотеатром.
 */
public interface IAdministrator extends IUser {

    /**
     * Создать новый фильм в системе.
     *
     * @param name     Название фильма.
     * @param duration Продолжительность фильма.
     * @return Информация о созданном фильме.
     * @throws FilmCreationException Если не удалось создать фильм.
     */
    IFilm createFilm(String name, int duration) throws FilmCreationException;

    /**
     * Создать новый сеанс в системе.
     *
     * @param film      Фильм для сеанса.
     * @param hall      Кинозал для сеанса.
     * @param dateTime  Дата и время сеанса.
     * @return Информация о созданном сеансе.
     * @throws SessionCreationException Если не удалось создать сеанс.
     */
    ISession createSession(IFilm film, ICinemaHall hall, LocalDateTime dateTime) throws SessionCreationException;

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
    IProduct createProduct(String name, BigDecimal price, String description,
                           int stockQuantity, Currency currency) throws ProductCreationException;

    /**
     * Удалить фильм из системы.
     *
     * @param film Фильм для удаления.
     * @throws FilmDeletionException Если не удалось удалить фильм.
     */
    void deleteFilm(IFilm film) throws FilmDeletionException;

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