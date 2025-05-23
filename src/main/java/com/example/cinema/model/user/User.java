package com.example.cinema.model.user;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("users")

/**
 * Класс User представляет пользователя системы (клиент, сотрудник или администратор).
 */
public class User extends AbstractUser {
    public User(String email, String firstName, String lastName, String password, Role role, String userLogin) {
        super(email, firstName, lastName, password, role, userLogin);
    }

    public User(String name, String username, String hashedPassword) {
    }
}