package com.cinema.model.order;

import com.cinema.model.product.IProduct;
import com.cinema.model.ticket.ITicket;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Интерфейс IOrder предоставляет контракт для класса, представляющего заказ в системе управления кинотеатром.
 */
public interface IOrder {

    /**
     * Получить список билетов, входящих в этот заказ.
     *
     * @return Список билетов заказа.
     */
    List<ITicket> getTickets();

    /**
     * Добавить билет в этот заказ.
     *
     * @param ticket Билет, который следует добавить в заказ.
     */
    void addTicket(ITicket ticket);

    /**
     * Удалить билет из этого заказа.
     *
     * @param ticket Билет, который следует удалить из заказа.
     */
    void removeTicket(ITicket ticket);

    /**
     * Получить статус этого заказа (например, новый, подтвержденный, отмененный).
     *
     * @return Статус заказа.
     */
    OrderStatus getStatus();

    /**
     * Получить карту продуктов, входящих в этот заказ, с указанием количества каждого продукта.
     *
     * @return Карта продуктов и их количества.
     */

    Map<IProduct, Integer> getProducts();

    /**
     * Добавить продукт в этот заказ с указанием количества.
     *
     * @param product Продукт, который следует добавить в заказ.
     * @param quantity Количество продукта.
     */
    void addProduct(IProduct product, int quantity);

    /**
     * Удалить продукт из этого заказа.
     *
     * @param product Продукт, который следует удалить из заказа.
     */
    void removeProduct(IProduct product);

    /**
     * Установить статус для этого заказа.
     *
     * @param status Новый статус заказа.
     */
    void setStatus(OrderStatus status);

    /**
     * Получить общую стоимость этого заказа, рассчитывая сумму стоимости всех билетов в заказе.
     *
     * @return Общая стоимость заказа.
     */
    BigDecimal getTotalPrice();

    /**
     * Оплатить заказ, изменяя его статус и обновляя статус связанных билетов.
     */
    void pay();
}
