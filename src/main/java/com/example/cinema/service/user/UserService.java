package com.example.cinema.service.user;

import com.example.cinema.model.user.User;
import com.example.cinema.repository.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Добавляем аннотацию @Service
public class UserService implements IUserService {

    private final IUserRepository userRepository; // Добавляем поле для UserRepository
    private final BCryptPasswordEncoder passwordEncoder; // Добавляем поле для BCryptPasswordEncoder

    @Autowired // Добавляем аннотацию @Autowired для внедрения зависимостей через конструктор
    public UserService(IUserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(String name, String userLogin, String password) {
        if (userRepository.findByUserLogin(userLogin) != null) {
            throw new IllegalArgumentException("Пользователь с таким логином уже существует.");
        }

        // Хешируем пароль с использованием BCrypt
        String hashedPassword = passwordEncoder.encode(password);

        User user = new User(name, userLogin, hashedPassword); // Сохраняем хешированный пароль
        userRepository.save(user); // Сохраняем пользователя в базу данных
        return user;
    }

    @Override
    public User findByUsername(String userLogin) {
        return userRepository.findByUserLogin(userLogin); // Используем репозиторий для поиска
    }

    @Override
    public User findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id); // Используем репозиторий для поиска
        return userOptional.orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll(); // Используем репозиторий для получения всех пользователей
    }
}