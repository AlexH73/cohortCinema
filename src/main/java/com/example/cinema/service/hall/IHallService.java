package com.example.cinema.service.hall;

import com.example.cinema.model.hall.Hall;

import java.util.List;

/**
 * Интерфейс IHallService определяет контракт для управления залами в системе кинотеатра.
 */
public interface IHallService {

    /**
     * Добавить новый зал.
     *
     * @param hall Зал для добавления.
     */
    void addHall(Hall hall);

    /**
     * Удалить зал по его уникальному идентификатору.
     *
     * @param hallId Уникальный ID зала.
     * @return true, если зал был успешно удалён, иначе false.
     */
    boolean removeHallById(String hallId);

    /**
     * Получить зал по его уникальному идентификатору.
     *
     * @param hallId Уникальный ID зала.
     * @return Найденный зал или null, если не найден.
     */
    Hall getHallById(String hallId);

    /**
     * Получить список всех залов.
     *
     * @return Список всех доступных залов.
     */
    List<Hall> getAllHalls();
}
