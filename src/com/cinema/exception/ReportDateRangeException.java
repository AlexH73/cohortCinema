package com.cinema.exception;

/**
 * Исключение, выбрасываемое при ошибке в датах отчёта.
 */
public class ReportDateRangeException extends RuntimeException {
    public ReportDateRangeException(String message) {
        super(message);
    }
}
