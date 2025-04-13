package com.cinema.service.user;

import com.cinema.model.user.User;

import java.util.List;

public interface IUserService {

    /**
     * Зарегистрировать нового пользователя.
     * @param name Имя
     * @param login Логин
     * @param password Пароль
     * @return Новый пользователь
     */
    User registerUser(String name, String login, String password);

    /**
     * Найти пользователя по логину.
     */
    User findByLogin(String login);

    /**
     * Найти пользователя по ID.
     */
    User findById(String id);

    /**
     * Получить всех пользователей.
     */
    List<User> getAllUsers();
}

