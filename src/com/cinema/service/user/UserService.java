package com.cinema.service.user;

import com.cinema.model.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserService implements IUserService {

    private final List<User> users = new ArrayList<>();

    @Override
    public User registerUser(String name, String login, String password) {
        if (findByLogin(login) != null) {
            throw new IllegalArgumentException("Пользователь с таким логином уже существует.");
        }

        User user = new User(UUID.randomUUID().toString(), name, login, password);
        users.add(user);
        return user;
    }

    @Override
    public User findByLogin(String login) {
        Optional<User> user = users.stream()
                .filter(u -> u.getLogin().equalsIgnoreCase(login))
                .findFirst();
        return user.orElse(null);
    }

    @Override
    public User findById(String id) {
        Optional<User> user = users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
        return user.orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
}

