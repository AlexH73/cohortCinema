package com.cinema.service.film;

import com.cinema.model.film.Film;
import com.cinema.model.film.Genre;
import com.cinema.util.exceptions.FilmCreationException;
import java.util.List;

/**
 * Интерфейс IFilmService предоставляет методы для управления фильмами в системе.
 */
public interface IFilmService {

    /**
     * Создает новый фильм с заданными параметрами.
     *
     * @param name     Название фильма. Не может быть null или пустым.
     * @param duration Длительность фильма в минутах. Должна быть положительным числом.
     * @return Созданный фильм (объект класса Film).
     * @throws FilmCreationException Если не удалось создать фильм (например, если параметры невалидны).
     */
    Film createFilm(String name, int duration) throws FilmCreationException;

    /**
     * Получает фильм по его уникальному идентификатору.
     *
     * @param id Уникальный идентификатор фильма. Должен быть положительным числом.
     * @return Фильм с заданным ID, или null, если фильм не найден.
     */
    Film getFilmById(Long id);

    /**
     * Получает список всех фильмов в системе.
     *
     * @return Список всех фильмов. Если фильмов нет, возвращает пустой список.
     */
    List<Film> getAllFilms();

    /**
     * Обновляет информацию о фильме.
     *
     * @param film Фильм с обновленной информацией. ID фильма должен быть установлен.
     * @return Обновленный фильм (объект класса Film).
     * @throws IllegalArgumentException Если фильм с заданным ID не найден.
     */
    Film updateFilm(Film film) throws IllegalArgumentException;

    /**
     * Удаляет фильм по его уникальному идентификатору.
     *
     * @param id Уникальный идентификатор фильма, который нужно удалить.
     * @throws IllegalArgumentException Если фильм с заданным ID не найден.
     */
    void deleteFilm(Long id) throws IllegalArgumentException;

    /**
     * Получает список фильмов по заданному жанру.
     *
     * @param genre Жанр фильма. Не может быть null.
     * @return Список фильмов с заданным жанром. Если фильмов с заданным жанром нет, возвращает пустой список.
     */
    List<Film> getFilmsByGenre(Genre genre);
}
