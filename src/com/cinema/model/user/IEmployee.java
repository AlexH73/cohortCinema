package com.cinema.model.user;

import com.cinema.model.session.ISession;
import com.cinema.util.exceptions.MaxSessionsExceededException;

import java.util.Set;

/**
 * Интерфейс IEmployee предоставляет контракт для класса, представляющего сотрудника в системе управления кинотеатром.
 */
public interface IEmployee extends IUser {

    /**
     * Получить должность сотрудника.
     *
     * @return Должность сотрудника.
     */
    String getPosition();

    /**
     * Установить новую должность сотруднику.
     *
     * @param position Новая должность сотрудника.
     */
    void setPosition(String position);

    /**
     * Получить список сеансов, за которые сотрудник отвечает.
     *
     * @return Список сеансов.
     */
    Set<ISession> getManagedSessions();

    /**
     * Добавить сеанс, за который сотрудник будет отвечать.
     *
     * @param session Сеанс, за который сотрудник будет отвечать.
     * @throws MaxSessionsExceededException Если сотрудник уже отвечает за максимальное количество сеансов.
     */
    void addManagedSession(ISession session) throws MaxSessionsExceededException;

    /**
     * Удалить сеанс, за который сотрудник отвечает.
     *
     * @param session Сеанс, за который сотрудник перестает отвечать.
     */
    void removeManagedSession(ISession session);

    /**
     * Получить максимальное количество сеансов, за которые может отвечать сотрудник.
     *
     * @return Максимальное количество сеансов.
     */
    int getMaxManagedSessions();

    /**
     * Установить максимальное количество сеансов, за которые может отвечать сотрудник.
     *
     * @param maxManagedSessions Максимальное количество сеансов.
     */
    void setMaxManagedSessions(int maxManagedSessions);
}