# 🎬 Cinema Management System

📁 Репозиторий: https://github.com/AlexH73/cohortCinema

## Описание проекта

Cinema — это консольное приложение на языке Java, предназначенное для управления кинотеатром. Система позволяет работать с пользователями (клиенты, сотрудники, администраторы), управлять залами, сеансами, билетами, заказами и продуктами. Реализована поддержка отчётности.

Проект разрабатывается в учебных целях в рамках обучения Full-Stack разработке.

---

## ✨ Основной функционал

- 👤 Авторизация пользователей (клиенты, сотрудники, администраторы)
- 🎟 Управление фильмами, залами и сеансами (а также показ зала со свободными местами и занятыми)
- 🪑 Бронирование мест и продажа билетов (включая скидки для студентов и пенсионеров)
- 🍿 Управление продуктами (напитки, еда и т.д.)
- 📦 Оформление заказов
- 📊 Генерация отчётов
- 😲 Просмотр статистики посещений


---

## 🧱 Структура проекта

<details>
<summary><code>com.example.cinema</code> — корневой пакет</summary>

- `model` — модели и интерфейсы сущностей:
  - `film`, `hall`, `session`, `ticket`, `order`, `product`, `user`

- `service` — бизнес-логика
  - `report` — генерация отчётов

- `repository` — работа с файлами / данными

- `exception` — пользовательские исключения

- `util` — вспомогательные утилиты

- `ui` — пользовательский интерфейс (консоль)
</details>

---

## 🛠 Технологии

- Язык: **Java**
- Хранилище: **Файловая система**
- Архитектура: **MVC + интерфейсы**
- Инструменты: `Git`, `JDK 17+`, `Maven` (по желанию)

---

## 📂 Установка и запуск

1. Клонируйте репозиторий:
```bash
git clone https://github.com/AlexH73/cohortCinema.git
cd cohortCinema
```

2. Скомпилируйте и запустите:
```bash
javac com/cinema/ui/ConsoleUI.java
java com.example.cinema.ui.ConsoleUI
```

---

## 🧑‍💻 Авторы

- **AlexH73** – [GitHub Profile](https://github.com/AlexH73), [Slack Profile](https://ait-tr.slack.com/team/U07V574LHN0)
- **Juliaaa25** - [GitHub Profile](https://github.com/Juliaaa25), [Slack Profile](https://ait-tr.slack.com/team/U07V2JYFEV9)

---

## 📌 Заметки

- Проект на стадии разработки.
- Возможны изменения в структуре и логике приложения.
- Принимаются предложения по улучшению через Issues / Pull Requests.

---

## 📜 Лицензия

Проект доступен под лицензией **MIT**. Подробности в файле [LICENSE](LICENSE).
