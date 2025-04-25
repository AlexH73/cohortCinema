package com.cinema.repository.user;

import com.cinema.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *  Интерфейс репозитория для сущности {@link User}.
 *  Предоставляет методы для доступа к данным пользователей в базе данных.
 *  Расширяет интерфейс {@link JpaRepository} для наследования стандартных операций CRUD.
 */
@Repository
public interface IUserRepository extends JpaRepository<User, String> {

    /**
     *  Находит пользователя в базе данных по его логину.
     *
     *  @param login Логин пользователя, которого нужно найти.
     *  @return Объект {@link User}, представляющий пользователя, найденного по логину, или {@code null}, если пользователь не найден.
     */
    User findByLogin(String login);

    /**
     * Находит пользователя в базе данных по его ID.
     *
     * @param id ID пользователя, которого нужно найти.
     * @return Объект {@link User}, представляющий пользователя, найденного по ID, или {@link Optional#empty() Optional.empty()}, если пользователь не найден.
     */
    Optional<User> findById(String id);
}