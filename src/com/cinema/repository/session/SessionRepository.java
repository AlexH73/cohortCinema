package com.cinema.repository.session;

import com.cinema.model.session.ISession;
import com.cinema.model.film.IFilm;
import java.util.*;

/**
 * SessionRepository — реализация хранилища сеансов в памяти.
 */
public class SessionRepository implements ISessionRepository {

    private final Map<String, ISession> sessions = new HashMap<>();

    @Override
    public void save(ISession session) {
        sessions.put(session.getId(), session); // перезапись по ID, если уже есть
    }

    @Override
    public boolean deleteById(String id) {
        return sessions.remove(id) != null;
    }

    @Override
    public Optional<ISession> findById(String id) {
        return Optional.ofNullable(sessions.get(id));
    }

    @Override
    public List<ISession> findAll() {
        return new ArrayList<>(sessions.values());
    }

    @Override
    public List<ISession> findByFilmId(String filmId) {
        List<ISession> result = new ArrayList<>();
        for (ISession session : sessions.values()) {
            if (session.getFilm() != null && session.getFilm().getId().equals(filmId)) {
                result.add(session);
            }
        }
        return result;
    }

    @Override
    public List<ISession> findByHallId(String hallId) {
        List<ISession> result = new ArrayList<>();
        for (ISession session : sessions.values()) {
            if (session.getCinemaHall() != null && session.getCinemaHall().getId().equals(hallId)) {
                result.add(session);
            }
        }
        return result;
    }
}