package com.cinema.service.user;

import com.cinema.model.user.User;

import java.util.List;

public interface IUserService {

    /**
     * Зарегистрировать нового пользователя.
     * @param name Имя
     * @param username Логин
     * @param password Пароль
     * @return Новый пользователь
     */
    User registerUser(String name, String username, String password);

    /**
     * Найти пользователя по логину.
     */
    User findByUsername(String username);

    /**
     * Найти пользователя по ID.
     */
    User findById(Long id);

    /**
     * Получить всех пользователей.
     */
    List<User> getAllUsers();
}

