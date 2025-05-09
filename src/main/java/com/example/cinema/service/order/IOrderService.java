package com.example.cinema.service.order;

import com.example.cinema.model.order.Order;
import com.example.cinema.model.user.Customer;

import java.util.List;

/**
 * Сервисный слой для управления заказами.
 */
public interface IOrderService {

    /**
     * Создать новый пустой заказ для указанного клиента.
     *
     * @param user Клиент, для которого создаётся заказ.
     * @return Созданный заказ (со статусом NEW).
     */
    Order createOrder(Customer user);

    /**
     * Добавить билет к существующему заказу.
     *
     * @param orderId  ID заказа.
     * @param ticketId ID билета для добавления.
     * @return Обновлённый заказ.
     * @throws OrderNotFoundException       если заказ с таким ID не найден.
     * @throws TicketNotAvailableException  если билет уже продан или недоступен.
     * @throws InvalidOrderStateException   если заказ нельзя модифицировать (не в статусе NEW).
     */
    Order addTicket(Long orderId, Long ticketId);

    /**
     * Добавить продукт (в указанном количестве) к заказу.
     *
     * @param orderId   ID заказа.
     * @param productId ID продукта.
     * @param quantity  Количество единиц продукта.
     * @return Обновлённый заказ.
     * @throws OrderNotFoundException         если заказ не найден.
     * @throws ProductNotAvailableException   если на складе нет нужного количества продукта.
     * @throws InvalidOrderStateException     если заказ нельзя модифицировать.
     */
    Order addProduct(Long orderId, Long productId, int quantity);

    /**
     * Оплатить заказ: сменить статус на PAID и отметить все билеты как SOLD.
     *
     * @param orderId ID заказа.
     * @return Оплаченный заказ.
     * @throws OrderNotFoundException     если заказ не найден.
     * @throws InvalidOrderStateException если заказ нельзя оплатить (пустой или уже оплаченный/отменённый).
     */
    Order payOrder(Long orderId);

    /**
     * Отменить заказ: сменить статус на CANCELLED и вернуть билеты в AVAILABLE.
     *
     * @param orderId ID заказа.
     * @return Отменённый заказ.
     * @throws OrderNotFoundException     если заказ не найден.
     * @throws InvalidOrderStateException если заказ нельзя отменить (уже оплаченный или отменённый).
     */
    Order cancelOrder(Long orderId);

    /**
     * Найти заказ по его идентификатору.
     *
     * @param orderId ID заказа.
     * @return Найденный заказ.
     * @throws OrderNotFoundException если заказ не существует.
     */
    Order getOrderById(Long orderId);

    /**
     * Получить список всех заказов в системе.
     *
     * @return Список всех заказов.
     */
    List<Order> getAllOrders();

    /**
     * Получить все заказы указанного клиента.
     *
     * @param userId ID клиента.
     * @return Список заказов данного клиента.
     */
    List<Order> getOrdersByUser(Long userId);
}
