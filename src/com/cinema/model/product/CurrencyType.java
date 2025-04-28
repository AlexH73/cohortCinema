package com.cinema.model.product;

/**
 * Перечисление поддерживаемых валют.
 */
public enum CurrencyType {
    RUB("₽"),
    USD("$"),
    EUR("€");

    private final String symbol;

    CurrencyType(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Получить символ валюты (например, ₽ или $).
     *
     * @return Символ валюты.
     */
    public String getSymbol() {
        return symbol;
    }
}
