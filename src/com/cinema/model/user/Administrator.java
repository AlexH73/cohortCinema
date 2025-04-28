package com.cinema.model.user;

import com.cinema.model.film.Film;
import com.cinema.model.film.Genre;
import com.cinema.model.film.IFilm;
import com.cinema.model.hall.ICinemaHall;
import com.cinema.model.product.CurrencyType;
import com.cinema.model.product.IProduct;
import com.cinema.model.product.Product;
import com.cinema.model.session.ISession;
import com.cinema.model.session.Session;
import com.cinema.repository.film.FilmRepository;
import com.cinema.repository.product.ProductRepository;
import com.cinema.repository.session.SessionRepository;
import com.cinema.service.report.IReportGenerator;
import com.cinema.util.exceptions.FilmCreationException;
import com.cinema.util.exceptions.FilmDeletionException;
import com.cinema.util.exceptions.ProductCreationException;
import com.cinema.util.exceptions.ProductDeletionException;
import com.cinema.util.exceptions.SessionCreationException;
import com.cinema.util.exceptions.SessionDeletionException;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@DiscriminatorValue("ADMINISTRATOR")
public class Administrator extends AbstractUser implements IAdministrator {

    @Transient // Это поле не сохраняется в БД
    private static final Logger logger = LoggerFactory.getLogger(Administrator.class);

    @Transient
    private FilmRepository filmRepository;

    @Transient
    private SessionRepository sessionRepository;

    @Transient
    private IReportGenerator reportGenerator;

    @Autowired
    @Transient
    private final ProductRepository productRepository;

    // Основной конструктор для создания нового администратора вручную
    public Administrator(String email, String firstName, String lastName, String password, String userLogin,
                         ProductRepository productRepository) {
        super(email, firstName, lastName, password, Role.ADMIN, userLogin);
        this.productRepository = productRepository;
    }

    // Специальный метод для внедрения зависимостей вручную (если не используем Spring DI)
    public void setRepositories(FilmRepository filmRepository, SessionRepository sessionRepository, IReportGenerator reportGenerator) {
        this.filmRepository = filmRepository;
        this.sessionRepository = sessionRepository;
        this.reportGenerator = reportGenerator;
    }

    @Override
    @Transactional
    public IFilm createFilm(String title, String description, int durationMinutes, Genre genre,
                            double rating, String language, String posterUrl, LocalDate releaseDate) throws FilmCreationException {
        try {
            if (filmRepository.findByTitle(title)) {
                throw new FilmCreationException("Фильм с таким названием уже существует: " + title);
            }

            Film film = new Film(title, description, durationMinutes, genre, rating, language, posterUrl, releaseDate);
            return filmRepository.save(film);
        } catch (Exception e) {
            logger.error("Ошибка создания фильма: {}", e.getMessage());
            throw new FilmCreationException("Не удалось создать фильм: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public ISession createSession(IFilm film, ICinemaHall cinemaHall, LocalDateTime startTime, double ticketPrice) throws SessionCreationException {
        try {
            if (!isCinemaHallAvailable(cinemaHall, startTime, film.getDurationMinutes())) {
                throw new SessionCreationException("Кинозал недоступен в указанное время.");
            }

            Session session = new Session(film, cinemaHall, startTime, ticketPrice);
            return sessionRepository.save(session);
        } catch (Exception e) {
            logger.error("Ошибка создания сеанса: {}", e.getMessage());
            throw new SessionCreationException("Не удалось создать сеанс: " + e.getMessage());
        }
    }

    @Override
    public IProduct createProduct(String name, String description, BigDecimal price, int stockQuantity, CurrencyType currency) throws ProductCreationException {
        try {
            if (productRepository.existsByName(name)) {
                throw new ProductCreationException("Продукт с таким названием уже существует: " + name);
            }

            Product product = new Product(name, description, price, stockQuantity, currency);
            return productRepository.save(product);
        } catch (Exception e) {
            logger.error("Ошибка создания продукта: {}", e.getMessage());
            throw new ProductCreationException("Не удалось создать продукт: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteFilm(IFilm film) throws FilmDeletionException {
        try {
            filmRepository.delete((Film) film);
        } catch (Exception e) {
            logger.error("Ошибка удаления фильма: {}", e.getMessage());
            throw new FilmDeletionException("Не удалось удалить фильм: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteSession(ISession session) throws SessionDeletionException {
        try {
            sessionRepository.delete((Session) session);
        } catch (Exception e) {
            logger.error("Ошибка удаления сеанса: {}", e.getMessage());
            throw new SessionDeletionException("Не удалось удалить сеанс: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteProduct(IProduct product) throws ProductDeletionException {
        try {
            productRepository.delete((Product) product);
        } catch (Exception e) {
            logger.error("Ошибка удаления продукта: {}", e.getMessage());
            throw new ProductDeletionException("Не удалось удалить продукт: " + e.getMessage());
        }
    }

    @Override
    public IReportGenerator getReportGenerator() {
        return reportGenerator;
    }

    /**
     * Проверяет доступность зала для нового сеанса.
     * Здесь пока заглушка — в реальности нужно проверять пересечение времени сеансов.
     */
    private boolean isCinemaHallAvailable(ICinemaHall hall, LocalDateTime startTime, int durationMinutes) {
        List<ISession> sessions = sessionRepository.findByCinemaHall(hall);
        for (ISession session : sessions) {
            LocalDateTime sessionEndTime = session.getStartTime().plusMinutes(session.getFilm().getDurationMinutes());
            if (startTime.isBefore(sessionEndTime) && startTime.plusMinutes(durationMinutes).isAfter(session.getStartTime())) {
                return false;
            }
        }
        return true;
    }
}
