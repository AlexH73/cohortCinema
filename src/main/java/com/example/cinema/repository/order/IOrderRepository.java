package com.example.cinema.repository.order;

import com.example.cinema.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий заказов на основе Spring Data JPA.
 */
@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {

    /**
     * Найти все заказы по ID пользователя.
     * Spring Data сгенерирует нужный SQL автоматически по имени метода.
     */
    List<Order> findByUserId(Long userId);

    /**
     * (Пример кастомного JPQL-запроса, если понадобится)
     */
    @Query("SELECT o FROM Order o WHERE o.status = :status")
    List<Order> findAllByStatus(@Param("status") String status);
}
