package com.cinema.util.exceptions;

/**
 * Исключение, выбрасываемое при ошибке в датах отчёта.
 */
public class ReportDateRangeException extends RuntimeException {
    public ReportDateRangeException(String message) {
        super(message);
    }
}
