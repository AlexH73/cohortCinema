package com.cinema.model.user;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Абстрактный класс AbstractUser предоставляет базовую реализацию и хранение данных для пользователя.
 */
public abstract class AbstractUser implements IUser {
    protected String id;
    protected String username;
    protected String passwordHash;
    protected String passwordSalt;
    protected String email;
    protected String firstName;
    protected String lastName;
    protected UserRole role;
    protected LocalDateTime createdAt;
    protected boolean isActive;

    public AbstractUser(String username, String passwordHash, String passwordSalt, UserRole role, String email, String firstName, String lastName) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
        this.role = role;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdAt = LocalDateTime.now();
        this.isActive = true;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username; // Set the field
    }

    @Override
    public String getPasswordSalt() {
        return passwordSalt;
    }

    @Override
    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    @Override
    public String getPasswordHash() {
        return passwordHash;
    }

    @Override
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractUser that = (AbstractUser) o;
        return isActive == that.isActive && id.equals(that.id) && username.equals(that.username) && passwordHash.equals(that.passwordHash) && passwordSalt.equals(that.passwordSalt) && email.equals(that.email) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && role == that.role && createdAt.equals(that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, passwordHash, passwordSalt, email, firstName, lastName, role, createdAt, isActive);
    }
}