package com.cinema;


import com.cinema.controller.product.ConsoleOutputHandler;
import com.cinema.controller.product.ProductController;
import com.cinema.model.user.AbstractUser;
import com.cinema.model.user.Role;
import com.cinema.model.user.User;
import com.cinema.model.film.Film;
import com.cinema.model.film.Genre;
import com.cinema.model.hall.Hall;
import com.cinema.model.hall.HallType;
import com.cinema.model.user.Customer;
import com.cinema.repository.hall.HallRepository;
import com.cinema.repository.product.IProductRepository;
import com.cinema.repository.product.ProductRepository;
import com.cinema.service.hall.HallServiceImpl;
import com.cinema.service.hall.IHallService;
import com.cinema.service.product.ProductService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {

/*        System.out.println("Приложение запускается...");
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        System.out.println("Приложение запущено!");*/

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
        Film film1 = new Film("Интерстеллар", "Научно-фантастическая драма", 169, Genre.SCI_FI, 8.6, "EN", "", LocalDate.now());
        Film film2 = new Film("Брат", "Криминальная драма", 100, Genre.ACTION, 7.8, "RU", "", LocalDate.now());

        System.out.println("\n🎞️ Добавленные фильмы:");
        System.out.println(film1);
        System.out.println(film1.getReleaseDate());
        System.out.println(film2);

        // ==== 3. Создание пользователя-клиента ====
        Customer customer = new Customer("ivan_petrov", "12345");
        System.out.println("\n👤 Создан новый клиент:");
        System.out.println("Логин: " + customer.getUsername());
        System.out.println("Роль: " + customer.getRole());

        AbstractUser newUser = new User("abc@mail.tut", "Иван","Сидоров", "asdfg742", Role.STAFF, "Login");
        AbstractUser newUser1 = new User("abcde@mail.tut", "Сидор", "Иванов", "asdfg743", Role.MANAGER, "LoginA");

        System.out.println("\n👤 Создан новый "+ newUser.getRole() + ":");
        System.out.println("Логин: " + newUser.getUserLogin());
        System.out.println("Роль: " + newUser.getRole());
        System.out.println("Email: " + newUser.getEmail());
        System.out.println("Имя: " + newUser.getFirstName());
        System.out.println("Фамилия: " + newUser.getLastName());
        System.out.println("Дата регистрации: " + newUser.getCreatedAt());
        System.out.println("Id: " + newUser.getId());
        System.out.println(newUser);

        // Инициализация репозитория и сервиса
        IProductRepository tempRepo = new ProductRepository();
        ProductService productService = new ProductService(tempRepo);

        // Создание контроллера
        ProductController menu = new ProductController(
                productService,
                new ConsoleOutputHandler()
        );

        // Запуск меню
        menu.runProductMenu();
        System.out.println("--------------------------------------");
        System.out.println("✅ Инициализация завершена!");
    }
}
