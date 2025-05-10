package com.example.cinema.model.user;

import java.time.LocalDateTime;
import java.util.Objects;

import com.example.cinema.util.utils.PasswordUtils;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
/**
 * Абстрактный класс AbstractUser предоставляет базовую реализацию и хранение данных для пользователя.
 */
public abstract class AbstractUser{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_login", nullable = false, unique = true)
    private String userLogin;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    protected String email;

    @Column(name = "first_name", nullable = true)
    protected String firstName;

    @Column(name = "last_name", nullable = true)
    protected String lastName;

    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    protected boolean isActive = true;

    // Внедряем PasswordUtils (если нужно создавать пользователей в этом классе)
    @Transient // Не сохраняем в базе данных
    @Autowired
    private PasswordUtils passwordUtils;

    public AbstractUser() {
    }

    public AbstractUser(String email, String userLogin, Role role, String password) {
        this.email = email;
        this.userLogin = userLogin;
        this.role = role;
        this.password = PasswordUtils.hashPassword(password);
    }

    public AbstractUser(String email, String firstName, String lastName, String password, Role role, String userLogin) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = PasswordUtils.hashPassword(password);
        this.role = role;
        this.userLogin = userLogin;
    }

    public void setPassword(String password) {
        this.password = PasswordUtils.hashPassword(password); // Хешируем пароль
    }

    public boolean checkPassword(String rawPassword) {
        return PasswordUtils.checkPassword(rawPassword, this.password); // Сравниваем хеши
    }

    public String getPassword() {
        return password;
    }

    public Long getId() {
        return id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractUser that = (AbstractUser) o;
        return isActive == that.isActive &&
                id.equals(that.id) &&
                userLogin.equals(that.userLogin) &&
                email.equals(that.email) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                role == that.role &&
                createdAt.equals(that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userLogin, email, firstName, lastName, role, createdAt, isActive);
    }

    @Override
    public String toString() {
        return "AbstractUser{" +
                "id='" + id + '\'' +
                ", userLogin='" + userLogin + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                ", createdAt=" + createdAt +
                ", isActive=" + isActive +
                '}';
    }
}