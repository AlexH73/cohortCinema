package com.cinema.model.user;

import com.cinema.model.session.ISession;
import com.cinema.util.exceptions.MaxSessionsExceededException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс Employee представляет сотрудника кинотеатра.
 */
public class Employee extends AbstractUser implements IEmployee {

    private String position;
    private final Set<ISession> managedSessions = new HashSet<>();
    private int maxManagedSessions = 5; // Значение по умолчанию

    public Employee(String username, String passwordHash, String passwordSalt, UserRole role, String email, String firstName, String lastName) {
        super(username, passwordHash, passwordSalt, role, email, firstName, lastName);
    }

    @Override
    public String getPosition() {
        return position;
    }

    @Override
    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public Set<ISession> getManagedSessions() {
        return Collections.unmodifiableSet(managedSessions); // Защита от изменений извне
    }

    @Override
    public void addManagedSession(ISession session) throws MaxSessionsExceededException {
        if (managedSessions.size() >= maxManagedSessions) {
            throw new MaxSessionsExceededException("Сотрудник уже отвечает за максимальное количество сеансов.");
        }
        managedSessions.add(session);
    }

    @Override
    public void removeManagedSession(ISession session) {
        managedSessions.remove(session);
    }

    @Override
    public int getMaxManagedSessions() {
        return maxManagedSessions;
    }

    @Override
    public void setMaxManagedSessions(int maxManagedSessions) {
        this.maxManagedSessions = maxManagedSessions;
    }
}