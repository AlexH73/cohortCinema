package com.cinema.model.hall;

import java.util.UUID;

/**
 * Класс Hall представляет зал кинотеатра.
 */
public class Hall implements ICinemaHall {
    private final String id;
    private int hallNumber;
    private int rows;
    private int seatsPerRow;
    private int capacity;
    private HallType hallType;

    public Hall(int hallNumber, int rows, int seatsPerRow, HallType hallType) {
        validate(hallNumber, rows, seatsPerRow);
        this.id = UUID.randomUUID().toString();
        this.hallNumber = hallNumber;
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
        this.capacity = rows * seatsPerRow;
        this.hallType = hallType;
    }

    private void validate(int hallNumber, int rows, int seatsPerRow) {
        if (hallNumber <= 0 || rows <= 0 || seatsPerRow <= 0) {
            throw new IllegalArgumentException("Некорректные параметры зала.");
        }
    }

    // Реализация методов ICinemaHall
    @Override
    public int getHallNumber() {
        return hallNumber;
    }

    @Override
    public void setHallNumber(int hallNumber) {
        if (hallNumber <= 0) {
            throw new IllegalArgumentException("Номер зала должен быть > 0.");
        }
        this.hallNumber = hallNumber;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public void setCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Вместимость должна быть > 0.");
        }
        this.capacity = capacity;
    }

    @Override
    public void setHallType(HallType hallType) {
        this.hallType = hallType;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if (rows <= 0) {
            throw new IllegalArgumentException("Ряды должны быть > 0.");
        }
        this.rows = rows;
        this.capacity = rows * seatsPerRow;
    }

    public int getSeatsPerRow() {
        return seatsPerRow;
    }

    public void setSeatsPerRow(int seatsPerRow) {
        if (seatsPerRow <= 0) {
            throw new IllegalArgumentException("Места в ряду должны быть > 0.");
        }
        this.seatsPerRow = seatsPerRow;
        this.capacity = rows * seatsPerRow;
    }

    public String getId() {
        return id;
    }

    /**
     * Возвращает общее количество мест в зале.
     */
    public int getTotalSeats() {
        return rows * seatsPerRow;
    }

    @Override
    public String toString() {
        return "Hall{" +
                "id='" + id + '\'' +
                ", hallNumber=" + hallNumber +
                ", capacity=" + capacity +
                ", hallType=" + hallType +
                '}';
    }
}
