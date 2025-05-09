package com.example.cinema.service.film;

import com.example.cinema.model.film.Genre;
import com.example.cinema.util.exceptions.FilmCreationException;
import com.example.cinema.model.film.Film;
import com.example.cinema.repository.film.FilmRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Класс FilmService реализует интерфейс IFilmService и предоставляет методы для управления фильмами в системе.
 */
@Service
public class FilmService implements IFilmService {

    private static final Logger logger = LoggerFactory.getLogger(FilmService.class);

    @Autowired
    private FilmRepository filmRepository;

    /**
     * Создает новый фильм с заданными параметрами.
     *
     * @param name     Название фильма. Не может быть null или пустым.
     * @param duration Длительность фильма в минутах. Должна быть положительным числом.
     * @return Созданный фильм (объект класса Film).
     * @throws FilmCreationException Если не удалось создать фильм (например, если параметры невалидны).
     */
    @Override
    @Transactional
    public Film createFilm(String name, int duration) throws FilmCreationException {
        try {
            // Константы для значений по умолчанию
            final String DEFAULT_DESCRIPTION = "Описание по умолчанию";
            final Genre DEFAULT_GENRE = Genre.ACTION;
            final double DEFAULT_RATING = 5.0;
            final String DEFAULT_LANGUAGE = "Неизвестно";
            final String DEFAULT_POSTER_URL = "default_poster.jpg";

            // Создаем Film с полной информацией
            Film film = new Film(name, DEFAULT_DESCRIPTION, duration, DEFAULT_GENRE, DEFAULT_RATING, DEFAULT_LANGUAGE, DEFAULT_POSTER_URL, LocalDate.now());

            // Сохраняем фильм в базе данных
            return filmRepository.save(film);
        } catch (Exception e) {
            logger.error("Не удалось создать фильм", e);
            throw new FilmCreationException("Не удалось создать фильм: " + e.getMessage());
        }
    }

    /**
     * Получает фильм по его уникальному идентификатору.
     *
     * @param id Уникальный идентификатор фильма. Должен быть положительным числом.
     * @return Фильм с заданным ID, или null, если фильм не найден.
     */
    @Override
    public Film getFilmById(Long id) {
        Optional<Film> optionalFilm = filmRepository.findById(id);
        return optionalFilm.orElse(null);
    }

    /**
     * Получает список всех фильмов в системе.
     *
     * @return Список всех фильмов. Если фильмов нет, возвращает пустой список.
     */
    @Override
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    /**
     * Обновляет информацию о фильме.
     *
     * @param film Фильм с обновленной информацией. ID фильма должен быть установлен.
     * @return Обновленный фильм (объект класса Film).
     * @throws IllegalArgumentException Если фильм с заданным ID не найден.
     */
    @Override
    @Transactional
    public Film updateFilm(Film film) throws IllegalArgumentException {
        if (filmRepository.existsById(film.getId())) {
            return filmRepository.save(film);
        } else {
            throw new IllegalArgumentException("Фильм с ID " + film.getId() + " не найден.");
        }
    }

    /**
     * Удаляет фильм по его уникальному идентификатору.
     *
     * @param id Уникальный идентификатор фильма, который нужно удалить.
     * @throws IllegalArgumentException Если фильм с заданным ID не найден.
     */
    @Override
    @Transactional
    public void deleteFilm(Long id) throws IllegalArgumentException {
        if (filmRepository.existsById(id)) {
            filmRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Фильм с ID " + id + " не найден.");
        }
    }

    /**
     * Получает список фильмов по заданному жанру.
     *
     * @param genre Жанр фильма. Не может быть null.
     * @return Список фильмов с заданным жанром. Если фильмов с заданным жанром нет, возвращает пустой список.
     */
    @Override
    public List<Film> getFilmsByGenre(Genre genre) {
        return filmRepository.findByGenre(genre);
    }
}