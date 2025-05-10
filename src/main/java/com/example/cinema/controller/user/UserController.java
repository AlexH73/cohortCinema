package com.example.cinema.controller.user;

import com.example.cinema.service.user.IUserService;
import com.example.cinema.util.utils.ConsoleOutputHandler;

public class UserController implements IUserController {

    private final IUserService userService;
    private final ConsoleOutputHandler console;

    public UserController(IUserService userService, ConsoleOutputHandler console) {
        this.userService = userService;
        this.console = console;
    }

    @Override
    public void registerUser() {
        // TODO: реализовать регистрацию
        console.printInfo("Функция регистрации пользователя пока не реализована.");
    }

    @Override
    public void showAllUsers() {
        userService.getAllUsers().forEach(user -> console.print(user.toString()));
    }
}
