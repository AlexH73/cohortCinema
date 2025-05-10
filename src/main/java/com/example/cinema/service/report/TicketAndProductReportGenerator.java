package com.example.cinema.service.report;

import com.example.cinema.util.exceptions.ReportDateRangeException;
import com.example.cinema.model.order.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.text.NumberFormat;

/**
 * Генератор отчётов по билетам и продуктам.
 */
public class TicketAndProductReportGenerator implements IReportGenerator {
    private final List<Order> orders;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public TicketAndProductReportGenerator(List<Order> orders) {
        this.orders = orders;
    }

    private LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Неверный формат даты. Используйте yyyy-MM-dd.", e);
        }
    }

    @Override
    public String generateProductSalesReport(String startDate, String endDate) {
        LocalDate start = parseDate(startDate);
        LocalDate end = parseDate(endDate);

        if (start.isAfter(end)) {
            throw new ReportDateRangeException("Начальная дата позже конечной!");
        }

        BigDecimal total = orders.stream()
                .filter(o -> {
                    LocalDateTime orderDate = o.getCreatedAt();
                    LocalDate orderLocalDate = orderDate.toLocalDate();
                    return (orderLocalDate.isEqual(start) || orderLocalDate.isAfter(start)) &&
                            (orderLocalDate.isEqual(end) || orderLocalDate.isBefore(end));
                })
                .flatMap(o -> o.getProducts().keySet().stream()) // Получаем ключи (продукты) из Map
                .map(p -> p.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Форматируем число с учетом локали и валюты
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
        String formattedTotal = currencyFormat.format(total);

        return "🧃 Отчет о продажах продуктов\n" +
                "Период: " + start + " - " + end + "\n" +
                "Общая сумма: " + formattedTotal;
    }

    @Override
    public String generateTicketSalesReport(String startDate, String endDate) {
        LocalDate start = parseDate(startDate);
        LocalDate end = parseDate(endDate);

        if (start.isAfter(end)) {
            throw new ReportDateRangeException("Начальная дата позже конечной!");
        }

        BigDecimal total = orders.stream()
                .filter(o -> {
                    LocalDateTime orderDate = o.getCreatedAt();
                    LocalDate orderLocalDate = orderDate.toLocalDate();
                    return (orderLocalDate.isEqual(start) || orderLocalDate.isAfter(start)) &&
                            (orderLocalDate.isEqual(end) || orderLocalDate.isBefore(end));
                })
                .flatMap(o -> o.getTickets().stream())
                .map(t -> BigDecimal.valueOf(t.getPrice())) // Преобразуем double в BigDecimal
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        // Форматируем число с учетом локали и валюты
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
        String formattedTotal = currencyFormat.format(total);

        return "💳 Отчет о продажах билетов\n" +
                "Период: " + start + " - " + end + "\n" +
                "Общая сумма: " + formattedTotal;
    }
}