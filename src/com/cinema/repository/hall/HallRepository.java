package com.cinema.repository.hall;

import com.cinema.model.hall.Hall;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс HallRepository реализует интерфейс IHallRepository и управляет хранилищем залов в памяти.
 */
public class HallRepository implements IHallRepository {
    public final Map<String, Hall> halls = new HashMap<>();

    /**
     * Сохраняет новый зал в хранилище или обновляет существующий.
     *
     * @param hall Зал для сохранения.
     */
    @Override
    public void save(Hall hall) {
        halls.put(hall.getId(), hall);
    }

    /**
     * Удаляет зал по его уникальному идентификатору.
     *
     * @param hallId Уникальный ID зала.
     * @return true, если зал был успешно удалён, иначе false.
     */
    @Override
    public boolean deleteById(String hallId) {
        return halls.remove(hallId) != null;
    }

    /**
     * Ищет зал по его уникальному идентификатору.
     *
     * @param hallId Уникальный ID зала.
     * @return Найденный зал или null, если не найден.
     */
    @Override
    public Hall findById(String hallId) {
        return halls.get(hallId);
    }

    /**
     * Возвращает список всех залов.
     *
     * @return Список всех залов.
     */
    @Override
    public List<Hall> findAll() {
        return new ArrayList<>(halls.values());
    }
}
