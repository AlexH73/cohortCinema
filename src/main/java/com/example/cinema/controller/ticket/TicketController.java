package com.example.cinema.controller.ticket;

import com.example.cinema.model.session.Session;
import com.example.cinema.model.ticket.Ticket;
import com.example.cinema.model.hall.Seat;
import com.example.cinema.model.user.Customer;
import com.example.cinema.model.user.Role;
import com.example.cinema.service.session.ISessionService;
import com.example.cinema.service.ticket.ITicketService;
import com.example.cinema.util.exceptions.BookingException;
import com.example.cinema.util.exceptions.ReturnException;
import com.example.cinema.util.utils.ConsoleOutputHandler;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class TicketController implements ITicketController {

    private final ITicketService ticketService;
    private final ISessionService sessionService;
    private final ConsoleOutputHandler console;
    private final Customer currentUser;
    private final Scanner scanner;

    public TicketController(
            ITicketService ticketService,
            ISessionService sessionService,
            ConsoleOutputHandler console,
            Customer currentUser
    ) {
        this.ticketService = ticketService;
        this.sessionService = sessionService;
        this.console = console;
        this.currentUser = currentUser;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void buyTicket() {
        console.print("Введите ID сеанса: ");
        Long sessionId = Long.parseLong(scanner.nextLine());

        Session session = sessionService.getSessionById(sessionId);
        if (session == null) {
            console.printError("❌ Сеанс не найден.");
            return;
        }

        if (Duration.between(LocalDateTime.now(), session.getStartTime()).toMinutes() < 5) {
            console.printWarning("⏳ Нельзя купить билет менее чем за 5 минут до начала.");
            return;
        }

        console.print("Введите номер места: ");
        int seatNumber = Integer.parseInt(scanner.nextLine());

        Seat seat = new Seat(seatNumber); // Упрощено: без ряда
        double basePrice = session.getTicketPrice();
        double discount = isEligibleForDiscount(currentUser) ? 0.20 : 0.0;
        double finalPrice = basePrice * (1 - discount);

        try {
            Ticket ticket = ticketService.bookTicket(session, currentUser, seat, finalPrice);
            console.printSuccess("✅ Билет успешно куплен: " + ticket);
        } catch (BookingException e) {
            console.printError("❌ Ошибка бронирования: " + e.getMessage());
        }
    }

    @Override
    public void returnTicket() {
        console.print("Введите ID билета для возврата: ");
        Long ticketId = Long.parseLong(scanner.nextLine());

        try {
            Ticket ticket = ticketService.getTicketById(ticketId);
            if (ticket == null || !ticket.getUser().equals(currentUser)) {
                console.printError("❌ Билет не найден или не принадлежит вам.");
                return;
            }

            Session session = ticket.getSession();
            if (Duration.between(LocalDateTime.now(), session.getStartTime()).toMinutes() < 5) {
                console.printWarning("⏳ Нельзя вернуть билет менее чем за 5 минут до начала.");
                return;
            }

            ticketService.returnTicket(ticket);
            console.printSuccess("✅ Билет успешно возвращён.");
        } catch (ReturnException e) {
            console.printError("❌ Ошибка возврата: " + e.getMessage());
        }
    }

    @Override
    public void showMyTickets() {
        List<Ticket> tickets = ticketService.getTicketsByCustomer(currentUser);
        if (tickets.isEmpty()) {
            console.printInfo("📭 У вас нет билетов.");
        } else {
            console.printInfo("🎟 Ваши билеты:");
            tickets.forEach(ticket -> console.print(ticket.toString()));
        }
    }

    private boolean isEligibleForDiscount(Customer user) {
        return user.getRole() == Role.STUDENT || user.getRole() == Role.PENSIONER;
    }
}