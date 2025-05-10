package com.example.cinema;

import com.example.cinema.controller.product.ConsoleOutputHandler;
import com.example.cinema.controller.product.ProductController;
import com.example.cinema.model.film.Film;
import com.example.cinema.model.film.Genre;
import com.example.cinema.model.hall.Hall;
import com.example.cinema.model.hall.HallType;
import com.example.cinema.model.order.Order;
import com.example.cinema.model.product.CurrencyType;
import com.example.cinema.model.product.IProduct;
import com.example.cinema.model.product.Product;
import com.example.cinema.model.user.Customer;
import com.example.cinema.model.user.AbstractUser;
import com.example.cinema.model.user.Role;
import com.example.cinema.model.user.User;
import com.example.cinema.repository.hall.HallRepository;
import com.example.cinema.repository.product.IProductRepository;
import com.example.cinema.service.hall.HallServiceImpl;
import com.example.cinema.service.hall.IHallService;
import com.example.cinema.service.product.IProductService;
import com.example.cinema.service.product.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.out.println("🎬 Добро пожаловать в систему управления кинотеатром!");
        System.out.println("--------------------------------------");
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

        IProductRepository productRepository = context.getBean(IProductRepository.class);
        IProductService productService = new ProductService(productRepository);
        ConsoleOutputHandler outputHandler = new ConsoleOutputHandler();
        ProductController productController = new ProductController(productService, outputHandler);

        // === Работа с залами ===
        IHallService hallService = new HallServiceImpl(new HallRepository());
        hallService.addHall(new Hall(1, 10, 20, HallType.IMAX));
        hallService.addHall(new Hall(2, 8, 15, HallType.STANDARD));
        hallService.getAllHalls().forEach(System.out::println);

        // === Работа с фильмами ===
        Film film1 = new Film("Интерстеллар", "Научная фантастика", 169, Genre.SCI_FI, 8.6, "EN", "", LocalDate.now());
        Film film2 = new Film("Брат", "Криминальная драма", 100, Genre.ACTION, 7.8, "RU", "", LocalDate.now());
        System.out.println(film1);
        System.out.println(film2);

        // === Работа с пользователями ===
        Customer customer = new Customer("ivan_petrov", "12345");
        AbstractUser newUser = new User("abc@mail.tut", "Иван", "Сидоров", "asdfg742", Role.STAFF, "Login");
        System.out.println("Логин: " + customer.getUsername());
        System.out.println("Роль: " + customer.getRole());
        System.out.println("Логин: " + newUser.getUserLogin());

        // === Работа с заказом ===
        Order order = new Order(customer);
        System.out.println(order);

        // === Работа с продуктами ===
/*        IProduct popcorn = new Product("PopCorn", "Кукуруза сладкая", new BigDecimal("100.00"), 50, CurrencyType.EUR);
        productService.createProduct(popcorn);
        productService.getAllProducts().forEach(System.out::println);*/

        productController.runProductMenu();

        System.out.println("✅ Инициализация завершена!");
    }
}
