package com.example.cinema.repository.session;

import com.example.cinema.model.hall.ICinemaHall;
import com.example.cinema.model.session.ISession;
import com.example.cinema.model.session.Session;

import java.util.*;

/**
 * SessionRepository — реализация хранилища сеансов в памяти.
 */
public class SessionRepository implements ISessionRepository {

    private final Map<String, ISession> sessions = new HashMap<>();

    @Override
    public ISession save(ISession session) {
        sessions.put(String.valueOf(session.getId()), session); // перезапись по ID, если уже есть
        return session;
    }

    @Override
    public boolean deleteById(Long id) {
        return sessions.remove(id) != null;
    }

    @Override
    public Object findById(Long id) {
        return Optional.ofNullable(sessions.get(id));
    }

    @Override
    public List<ISession> findAll() {
        return new ArrayList<>(sessions.values());
    }

    @Override
    public List<ISession> findByFilmId(Long filmId) {
        List<ISession> result = new ArrayList<>();
        for (ISession session : sessions.values()) {
            if (session.getFilm() != null && session.getFilm().getId().equals(filmId)) {
                result.add(session);
            }
        }
        return result;
    }

    @Override
    public List<ISession> findByHallId(Long hallId) {
        List<ISession> result = new ArrayList<>();
        for (ISession session : sessions.values()) {
            if (session.getCinemaHall() != null && session.getCinemaHall().getId().equals(hallId)) {
                result.add(session);
            }
        }
        return result;
    }

    public void delete(Session session) {
        ///  nuzhno realizovat'
    }

    public List<ISession> findByCinemaHall(ICinemaHall hall) {
        return null;
    }
}