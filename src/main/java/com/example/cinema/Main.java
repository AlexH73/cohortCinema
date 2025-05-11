package com.example.cinema;

import com.example.cinema.app.ConsoleApp;
import com.example.cinema.controller.order.IOrderController;
import com.example.cinema.controller.order.OrderController;
import com.example.cinema.controller.product.IProductController;
import com.example.cinema.controller.product.ProductController;
import com.example.cinema.controller.user.IUserController;
import com.example.cinema.controller.user.UserController;
import com.example.cinema.repository.order.IOrderRepository;
import com.example.cinema.repository.product.IProductRepository;
import com.example.cinema.repository.ticket.ITicketRepository;
import com.example.cinema.repository.user.IUserRepository;
import com.example.cinema.service.order.IOrderService;
import com.example.cinema.service.order.OrderServiceImpl;
import com.example.cinema.service.product.IProductService;
import com.example.cinema.service.product.ProductService;
import com.example.cinema.service.user.IUserService;
import com.example.cinema.service.user.UserService;
import com.example.cinema.util.utils.ConsoleOutputHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Точка входа в консольное приложение Cinema.
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Main.class, args);

        // подтянули бины-репозитории
        IProductRepository productRepo = ctx.getBean(IProductRepository.class);
        IOrderRepository   orderRepo   = ctx.getBean(IOrderRepository.class);
        IUserRepository    userRepo    = ctx.getBean(IUserRepository.class);
        ITicketRepository  ticketRepo  = ctx.getBean(ITicketRepository.class);

        // создали сервисы
        IProductService productService = new ProductService(productRepo);
        IOrderService   orderService   = new OrderServiceImpl(orderRepo, ticketRepo, productRepo);
        IUserService    userService    = new UserService(userRepo);

        // контроллеры
        ConsoleOutputHandler output = new ConsoleOutputHandler();
        IProductController prodCtrl  = new ProductController(productService, output);
        IOrderController   ordCtrl   = new OrderController(orderService, output);
        IUserController    usrCtrl   = new UserController(userService, output);

        // и наконец — «главный цикл»
        new ConsoleApp(prodCtrl, ordCtrl, usrCtrl, output).run();
    }
}
