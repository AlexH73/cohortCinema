package com.cinema.service.report;

import com.cinema.util.exceptions.ReportDateRangeException;
import com.cinema.model.order.Order;

import java.time.LocalDate;
import java.util.List;

/**
 * Генератор отчётов по билетам и продуктам.
 */
public class TicketAndProductReportGenerator implements IReportGenerator {
    private final List<Order> orders;

    public TicketAndProductReportGenerator(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String generateProductSalesReport(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        if (start.isAfter(end)) {
            throw new ReportDateRangeException("Начальная дата позже конечной!");
        }

        double total = orders.stream()
                .filter(Order::isPaid)
                .filter(o -> {
                    LocalDate date = o.getUser().getCreatedAt().toLocalDate(); // Временно
                    return (date.isEqual(start) || date.isAfter(start)) &&
                            (date.isEqual(end) || date.isBefore(end));
                })
                .flatMap(o -> o.getProducts().stream())
                .mapToDouble(p -> p.getPrice())
                .sum();

        return "🧃 Отчет о продажах продуктов\n" +
                "Период: " + start + " - " + end + "\n" +
                "Общая сумма: " + total + " €";
    }

    @Override
    public String generateTicketSalesReport(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        if (start.isAfter(end)) {
            throw new ReportDateRangeException("Начальная дата позже конечной!");
        }

        double total = orders.stream()
                .filter(Order::isPaid)
                .filter(o -> {
                    LocalDate date = o.getUser().getCreatedAt().toLokcalDate(); // Временно, потом заменим на дату заказа
                    return (date.isEqual(start) || date.isAfter(start)) &&
                            (date.isEqual(end) || date.isBefore(end));
                })
                .flatMap( o -> o.getTickets().stream())
                .mapToDouble(t -> t.getSession().getTicketPrice())
                .sum();

        return "💳 Отчет о продажах билетов\n" +
                "Период: " + start + " - " + end + "\n" +
                "Общая сумма: " + total + " €";
    }
}
