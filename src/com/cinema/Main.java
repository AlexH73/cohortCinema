package com.cinema;

import com.cinema.data.User;
import com.cinema.model.film.Film;
import com.cinema.model.film.Genre;
import com.cinema.model.hall.Hall;
import com.cinema.model.hall.HallType;
import com.cinema.model.user.Customer;
import com.cinema.data.User;
import com.cinema.repository.hall.HallRepository;
import com.cinema.service.hall.HallServiceImpl;
import com.cinema.service.hall.IHallService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("🎬 Добро пожаловать в систему управления кинотеатром!");
        System.out.println("--------------------------------------");

        // ==== 1. Работа с залами ====
        IHallService hallService = new HallServiceImpl(new HallRepository());

        Hall hall1 = new Hall(1, 10, 20, HallType.IMAX);
        Hall hall2 = new Hall(2, 8, 15, HallType.STANDARD);

        hallService.addHall(hall1);
        hallService.addHall(hall2);

        System.out.println("📋 Список всех залов:");
        List<Hall> allHalls = hallService.getAllHalls();
        for (Hall h : allHalls) {
            System.out.println(h);
        }

        // ==== 2. Работа с фильмами ====
        Film film1 = new Film("Интерстеллар", "Научно-фантастическая драма", 169, Genre.SCI_FI, 8.6, "EN", "", null);
        Film film2 = new Film("Брат", "Криминальная драма", 100, Genre.ACTION, 7.8, "RU", "", null);

        System.out.println("\n🎞️ Добавленные фильмы:");
        System.out.println(film1);
        System.out.println(film2);

        // ==== 3. Создание пользователя-клиента ====
        Customer customer = new Customer("ivan_petrov", "12345");
        System.out.println("\n👤 Создан новый клиент:");
        System.out.println("Логин: " + customer.getUsername());
        System.out.println("Роль: " + customer.getRole());

        User newUser = new User("pervyj", "PervyjUser", "Login", "asdfg742");

        System.out.println(newUser.getId() + newUser.getName() + newUser.getLogin());


        System.out.println("--------------------------------------");
        System.out.println("✅ Инициализация завершена!");
    }
}
