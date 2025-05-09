package com.example.cinema.repository.user;

import com.example.cinema.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *  Интерфейс репозитория для сущности {@link User}.
 *  Предоставляет методы для доступа к данным пользователей в базе данных.
 *  Расширяет интерфейс {@link JpaRepository} для наследования стандартных операций CRUD.
 */
@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    /**
     *  Находит пользователя в базе данных по его логину.
     *
     *  @param username Логин пользователя, которого нужно найти.
     *  @return Объект {@link User}, представляющий пользователя, найденного по логину, или {@code null}, если пользователь не найден.
     */
    User findByUsername(String username);

    /**
     * Находит пользователя в базе данных по его ID.
     *
     * @param id ID пользователя, которого нужно найти.
     * @return Объект {@link User}, представляющий пользователя, найденного по ID, или {@link Optional#empty() Optional.empty()}, если пользователь не найден.
     */
    Optional<User> findById(Long id);
}