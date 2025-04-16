package com.cinema.model.user;

import com.cinema.model.session.ISession;
import java.util.List;
import java.util.ArrayList;

public class Employee extends AbstractUser implements IEmployee {
    private String position;
    private List<ISession> managedSessions = new ArrayList<>();

    public Employee(String username, String password) {
        super(username, password, UserRole.STAFF);
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
    public List<ISession> getManagedSessions() {
        return managedSessions;
    }

    @Override
    public void addManagedSession(ISession session) {
        managedSessions.add(session);
    }

    @Override
    public void removeManagedSession(ISession session) {
        managedSessions.remove(session);
    }
}