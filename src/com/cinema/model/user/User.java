package com.cinema.model.user;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "users")

/**
 * Класс User представляет пользователя системы (клиент, сотрудник или администратор).
 */
public class User extends AbstractUser implements IUser {
    // Уникальный идентификатор пользователя (генерируется автоматически)
    //private final String id;

    // Имя пользователя
    //private String username;

    // Email пользователя (будет использоваться для авторизации)
    //private String email;

    //private String firstName;
    //private String lastName;

    // Хеш пароля
    //private String passwordHash;

    // Соль для хеширования пароля
    //private String passwordSalt;

    // Роль пользователя (CLIENT, STAFF, ADMIN)
    //private UserRole role;

    //private LocalDateTime createdAt;
    //private boolean isActive;

    /**
     * Конструктор создаёт нового пользователя с уникальным ID.
     */
    // Конструктор с 3 параметрами (более простой)
    public User(String username, String password, UserRole role) {
        super(username, "", "", role, "", "", ""); // Вызываем конструктор с 7 параметрами с значениями по умолчанию
        super.passwordHash = password;
    }

    // Конструктор с 7 параметрами (полная инициализация)
    public User(String username, String passwordHash, String passwordSalt, UserRole role, String email, String firstName, String lastName) {

        super.username = username;
        super.passwordHash = passwordHash;
        super.passwordSalt = passwordSalt;
        super.role = role;
        super.email = email;
        super.firstName = firstName;
        super.lastName = lastName;
        super.createdAt = LocalDateTime.now();
        super.isActive = true;
    }

    public User(String name, String login, String hashedPassword) {
    }

   /* // Геттеры и сеттеры для всех полей
    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
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
        this.username = username;
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
    public String getPasswordSalt() {
        return passwordSalt;
    }

    @Override
    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
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
*/
/*    @Override
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
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                ", createdAt=" + createdAt +
                ", isActive=" + isActive +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isActive == user.isActive && id.equals(user.id) && username.equals(user.username) && email.equals(user.email) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && passwordHash.equals(user.passwordHash) && passwordSalt.equals(user.passwordSalt) && role == user.role && createdAt.equals(user.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, firstName, lastName, passwordHash, passwordSalt, role, createdAt, isActive);
    }*/
}