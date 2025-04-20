package com.cinema.model.user;

import com.cinema.model.film.IFilm;
import com.cinema.model.film.Film;
import com.cinema.model.hall.ICinemaHall;
import com.cinema.model.product.IProduct;
import com.cinema.model.product.Product;
import com.cinema.model.session.ISession;
import com.cinema.service.report.IReportGenerator;
import com.cinema.util.exceptions.FilmCreationException;
import com.cinema.util.exceptions.FilmDeletionException;
import com.cinema.util.exceptions.SessionCreationException;
import com.cinema.util.exceptions.SessionDeletionException;
import com.cinema.util.exceptions.ProductCreationException;
import com.cinema.util.exceptions.ProductDeletionException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.UUID;

public class Administrator extends AbstractUser implements IAdministrator {

    private final IReportGenerator reportGenerator;

    public Administrator(String username, String password, String email, String firstName, String lastName, IReportGenerator reportGenerator) {
        super(username, hashPassword(password), generateSalt(), UserRole.ADMIN, email, firstName, lastName);
        this.reportGenerator = reportGenerator;
    }

    private static String hashPassword(String password) {
        // TODO: Replace with a secure hashing algorithm (e.g., bcrypt, scrypt)
        return UUID.randomUUID().toString(); // Dummy implementation for demonstration
    }

    private static String generateSalt() {
        return UUID.randomUUID().toString(); // Dummy implementation for demonstration
    }

    @Override
    public void setUsername(String username) {
        super.setUsername(username);
    }

    @Override
    public IFilm createFilm(String name, int duration) throws FilmCreationException {
        try {
            return new Film(name, duration);
        } catch (Exception e) {
            throw new FilmCreationException("Failed to create film: " + e.getMessage());
        }
    }

    @Override
    public ISession createSession(IFilm film, ICinemaHall hall, LocalDateTime dateTime) throws SessionCreationException {
        // TODO: Implement session creation logic
        return null;
    }

    @Override
    public IProduct createProduct(String name, BigDecimal price, String description, int stockQuantity, Currency currency) throws ProductCreationException {
        try {
            return new Product(name, description, price.doubleValue(), stockQuantity);
        } catch (Exception e) {
            throw new ProductCreationException("Failed to create product: " + e.getMessage());
        }
    }

    @Override
    public void deleteFilm(IFilm film) throws FilmDeletionException {
        // TODO: Implement film deletion logic
    }

    @Override
    public void deleteSession(ISession session) throws SessionDeletionException {
        // TODO: Implement session deletion logic
    }

    @Override
    public void deleteProduct(IProduct product) throws ProductDeletionException {
        // TODO: Implement product deletion logic
    }

    @Override
    public IReportGenerator getReportGenerator() {
        return reportGenerator;
    }
}