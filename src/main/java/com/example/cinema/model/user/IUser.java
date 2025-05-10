package com.example.cinema.model.user;

import java.time.LocalDateTime;

/**
 * Интерфейс IUser предоставляет контракт для класса, представляющего пользователя в системе управления кинотеатром.
 */
public interface IUser {

    /**
     * Получить идентификатор пользователя.
     *
     * @return Идентификатор пользователя.
     */
    Long getId();

    /**
     * Получить имя пользователя.
     *
     * @return Имя пользователя.
     */
    String getUsername();

    /**
     * Установить новое имя пользователя.
     *
     * @param username Новое имя пользователя.
     */
    void setUsername(String username);

    /**
     * Получить хеш пароля пользователя.
     *
     * @return Хеш пароля пользователя.
     */
    String getPasswordHash();

    /**
     * Установить новый хеш пароля пользователя.
     *
     * @param passwordHash Новый хеш пароля пользователя.
     */
    void setPasswordHash(String passwordHash);

    /**
     * Получить соль, использованную при хешировании пароля.
     *
     * @return Соль.
     */
    String getPasswordSalt();

    /**
     * Установить соль, использованную при хешировании пароля.
     *
     * @param passwordSalt Соль.
     */
    void setPasswordSalt(String passwordSalt);

    /**
     * Получить email пользователя.
     *
     * @return Email пользователя.
     */
    String getEmail();

    /**
     * Установить новый email пользователя.
     *
     * @param email Новый email пользователя.
     */
    void setEmail(String email);

    /**
     * Получить имя пользователя.
     *
     * @return Имя пользователя.
     */
    String getFirstName();

    /**
     * Установить имя пользователя.
     *
     * @param firstName Имя пользователя.
     */
    void setFirstName(String firstName);

    /**
     * Получить фамилию пользователя.
     *
     * @return Фамилия пользователя.
     */
    String getLastName();

    /**
     * Установить фамилию пользователя.
     *
     * @param lastName Фамилия пользователя.
     */
    void setLastName(String lastName);

    /**
     * Получить дату создания пользователя.
     *
     * @return Дата создания пользователя.
     */
    LocalDateTime getCreatedAt();

    /**
     * Проверить, активен ли пользователь.
     *
     * @return True, если пользователь активен, иначе false.
     */
    boolean isActive();

    /**
     * Установить активность пользователя.
     *
     * @param active Активность пользователя.
     */
    void setActive(boolean active);
}