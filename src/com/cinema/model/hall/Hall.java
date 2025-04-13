package com.cinema.model.hall;

import java.util.UUID;

/**
 * Класс Hall представляет зал кинотеатра.
 */
public class Hall {
    private final String id;
    private String name;
    private int rows;
    private int statsPerRow;

    public Hall(String name, int rows, int statsPerRow) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.rows = rows;
        this.statsPerRow = statsPerRow;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRows() {
        return rows;
    }

    public int getStatsPerRow() {
        return statsPerRow;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setStatsPerRow(int statsPerRow) {
        this.statsPerRow = statsPerRow;
    }

    /**
     * Возвращает общее количество мест в зале.
     */
    public int getTotalSeats() {
        return rows * statsPerRow;
    }

    @Override
    public String toString() {
        return "Hall{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", totalSeats=" + getTotalSeats() +
                '}';
    }
}
