package com.cinema.repository.hall;

import com.cinema.model.hall.Hall;
import java.util.List;

/**
 * Интерфейс IHallRepository определяет контракт для операций над залами в хранилище данных.
 */
public interface IHallRepository {

    /**
     * Сохраняет новый зал в хранилище.
     *
     * @param hall Зал для сохранения.
     */
    void save(Hall hall);

    /**
     * Удаляет зал по его уникальному идентификатору.
     *
     * @param hallId Уникальный ID зала.
     * @return true, если зал был успешно удалён, иначе false.
     */
    boolean deleteById(String hallId);

    /**
     * Ищет зал по его уникальному идентификатору.
     *
     * @param hallId Уникальный ID зала.
     * @return Найденный зал или null, если не найден.
     */
    Hall findById(String hallId);

    /**
     * Возвращает список всех залов.
     *
     * @return Список всех залов.
     */
    List<Hall> findAll();
}
