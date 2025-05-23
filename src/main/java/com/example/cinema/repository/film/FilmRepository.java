package com.example.cinema.repository.film;

import com.example.cinema.model.film.Film;
import com.example.cinema.model.film.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    List<Film> findByGenre(Genre genre);

    boolean findByTitle(String title);
}
