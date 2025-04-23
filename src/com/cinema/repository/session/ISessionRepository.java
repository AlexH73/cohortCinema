package com.cinema.repository.session;

import com.cinema.model.session.ISession;
import com.cinema.model.session.Session;

import java.util.List;
import java.util.Optional;

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
    void save(ISession session);

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
    Optional findById(String sessionId);

    /**
     * Возвращает список всех сеансов.
     *
     * @return Список всех доступных сеансов.
     */
    List<ISession> findAll();

    /**
     * Возвращает список сеансов, в которых участвует указанный фильм.
     *
     * @param filmId Уникальный ID фильма.
     * @return Список сеансов с этим фильмом.
     */
    List<ISession> findByFilmId(String filmId);

    /**
     * Возвращает список сеансов, проходящих в указанном зале.
     *
     * @param hallId Уникальный ID зала.
     * @return Список сеансов, проводимых в этом зале.
     */
    List<ISession> findByHallId(String hallId);
}

