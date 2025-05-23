package com.example.cinema.util.exceptions;

/**
 * Исключение, выбрасываемое при ошибке в датах отчёта.
 */
public class ReportDateRangeException extends RuntimeException {
    public ReportDateRangeException(String message) {
        super(message);
    }

    public ReportDateRangeException(String message, Throwable cause) {
        super(message, cause);
    }
}