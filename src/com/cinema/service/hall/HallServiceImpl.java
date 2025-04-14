package com.cinema.service.hall;

import com.cinema.model.hall.Hall;
import com.cinema.repository.hall.IHallRepository;

import java.util.List;

/**
 * Класс HallServiceImpl реализует интерфейс IHallService и управляет бизнес-логикой для залов.
 */
public class HallServiceImpl implements IHallService {

    private final IHallRepository hallRepository;

    /**
     * Конструктор с внедрением зависимости через интерфейс.
     * @param hallRepository Репозиторий залов.
     */
    public HallServiceImpl(IHallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    @Override
    public void addHall(Hall hall) {
        hallRepository.save(hall);
    }

    @Override
    public boolean removeHallById(String hallId) {
        return hallRepository.deleteById(hallId);
    }

    @Override
    public Hall getHallById(String hallId) {
        return hallRepository.findById(hallId);
    }

    @Override
    public List<Hall> getAllHalls() {
        return hallRepository.findAll();
    }

}
