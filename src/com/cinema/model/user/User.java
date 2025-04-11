package com.cinema.model.user;

import java.util.UUID;

/**
 * Класс User представляет пользователя системы (клиент, сотрудник или администратор).
 */
public class User {
    // Уникальный идентификатор пользователя (генерируется автоматически)
    private final String id;

    // Имя пользователя
    private String name;

    // Email пользователя (будет использоваться для авторизации)
    private String email;

    // Пароль пользователя (в будущем можно будет хэшировать)
    private String password;

    // Роль пользователя (CLIENT, STAFF, ADMIN)
    private UserRole role;

    /**
     * Конструктор создаёт нового пользователя с уникальным ID.
     */
    public User(UserRole role, String password, String name, String email) {
        this.id = UUID.randomUUID().toString(); // Генерация уникального ID
        this.role = role;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    // Геттеры и сеттеры для всех полей
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    // Удобный вывод информации о пользователе (без пароля)
    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                '}';
    }
}
