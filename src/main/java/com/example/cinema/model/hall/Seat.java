package com.example.cinema.model.hall;

import jakarta.persistence.*;
import java.util.Objects;

/**
 * Класс Seat представляет конкретное место в зале.
 * Каждое место определяется рядом и номером в ряду.
 * В будущем при необходимости сюда можно добавить статус (занято/свободно) или привязку к залу.
 */
@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Генерация уникального идентификатора
    private Long id; // Уникальный идентификатор места

    @Column(name = "seat_row", nullable = false)
    private int row;    // Номер ряда, в котором находится место

    @Column(name = "seat_number", nullable = false)
    private int number; // Номер места в ряду

    /**
     * Конструктор места
     * @param row номер ряда
     * @param number номер места
     */
    public Seat(int row, int number) {
        if (row <= 0 || number <= 0) {
            throw new IllegalArgumentException("Некорректный ряд или место.");
        }
        this.row = row;
        this.number = number;
    }

    // Пустой конструктор для Hibernate
    public Seat() {}

    // Геттер для номера ряда
    public int getRow() {
        return row;
    }

/*    // Сеттер для номера ряда (вдруг зал реорганизуют)
    public void setRow(int row) {
        this.row = row;
    }*/

    // Геттер для номера места
    public int getNumber() {
        return number;
    }

/*    // Сеттер для номера места
    public void setNumber(int number) {
        this.number = number;
    }*/

    /**
     * Удобный вывод информации о месте.
     */
    @Override
    public String toString() {
        return "Seat{row=" + row + ", number=" + number + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return row == seat.row && number == seat.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, number);
    }
}
