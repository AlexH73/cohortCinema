package com.cinema.model.user;

import com.cinema.model.film.IFilm;
import com.cinema.model.hall.ICinemaHall;
import com.cinema.model.product.IProduct;
import com.cinema.model.session.ISession;

public class Administrator extends AbstractUser implements IAdministrator {
    public Administrator(String username, String password) {
        super(username, password, UserRole.ADMIN);
    }

    @Override
    public IFilm createFilm(String name, int duration) {
        return new Film(name, duration);
    }

    @Override
    public ISession createSession(IFilm film, ICinemaHall hall, String dateTime) {
        // Реализация создания сеанса
        return null;
    }

    @Override
    public IProduct createProduct(String name, double price, String description, int stockQuantity) {
        return new Product(name, description, price, stockQuantity);
    }

    @Override
    public void deleteFilm(IFilm film) {
        // Реализация удаления фильма
    }

    @Override
    public void deleteSession(ISession session) {
        // Реализация удаления сеанса
    }

    @Override
    public void deleteProduct(IProduct product) {
        // Реализация удаления продукта
    }

    @Override
    public String generateTicketSalesReport(String startDate, String endDate) {
        // Реализация генерации отчета
        return null;
    }

    @Override
    public String generateProductSalesReport(String startDate, String endDate) {
        // Реализация генерации отчета
        return null;
    }
}