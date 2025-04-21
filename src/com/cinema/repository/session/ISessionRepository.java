package com.cinema.repository.session;

import com.cinema.model.session.Session;

import java.util.List;

/**
 * Интерфейс ISessionRepository определяет контракт для операций
 * над сущностями сеансов в хранилище данных.
 */
public interface ISessionRepository {

    /**
     * Сохраняет новый сеанс в хранилище или обновляет существующий.
     *
     * @param session Сеанс для сохранения.
     */
    void save(Session session);

    /**
     * Удаляет сеанс по его уникальному идентификатору.
     *
     * @param sessionId Уникальный ID сеанса.
     * @return true, если сеанс был успешно удалён, иначе false.
     */
    boolean deleteById(String sessionId);

    /**
     * Ищет сеанс по его уникальному идентификатору.
     *
     * @param sessionId Уникальный ID сеанса.
     * @return Найденный сеанс или null, если не найден.
     */
    Session findById(String sessionId);

    /**
     * Возвращает список всех сеансов.
     *
     * @return Список всех доступных сеансов.
     */
    List<Session> findAll();

    /**
     * Возвращает список сеансов, в которых участвует указанный фильм.
     *
     * @param filmId Уникальный ID фильма.
     * @return Список сеансов с этим фильмом.
     */
    List<Session> findByFilmId(String filmId);

    /**
     * Возвращает список сеансов, проходящих в указанном зале.
     *
     * @param hallId Уникальный ID зала.
     * @return Список сеансов, проводимых в этом зале.
     */
    List<Session> findByHallId(String hallId);
}

