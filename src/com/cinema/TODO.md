# ✅ TODO — План разработки проекта "Cinema"

## 📌 Глобальный план (по слоям MVC)

### ✅ 1. Model (Модель — бизнес-логика и данные)
- [x] User + IUser
- [x] UserRole (enum)
- [x] Product + IProductService
- [x] ProductService
- [x] Product (модель)
- [x] ProductType (enum, если нужно)
- [ ] Ticket
- [ ] Seat
- [ ] Movie
- [ ] Hall
- [ ] Session
- [ ] Order
- [ ] OrderItem
- [ ] ProductOrder
- [ ] Custom Exception (например, InvalidDateRangeException)

### ✅ 2. Service (Сервисный слой)
- [x] IProductService
- [x] ProductService
- [ ] ITicketService
- [ ] TicketService
- [ ] IMovieService
- [ ] MovieService
- [ ] IOrderService
- [ ] OrderService
- [ ] IReportGenerator (интерфейс)
- [ ] ReportService (реализация)

### ✅ 3. Controller (Управляющий слой)
- [x] ProductController
- [ ] TicketController
- [ ] MovieController
- [ ] UserController
- [ ] AdminController (основное админ-меню)
- [ ] ReportController

### ✅ 4. View (Консольный пользовательский интерфейс)
- [ ] ApplicationRunner / MainMenu
- [ ] Меню для клиента
- [ ] Меню для сотрудника
- [ ] Меню администратора

### 🧪 5. Тестирование
- [ ] Покрытие юнит-тестами (опционально)
- [ ] Тесты для ProductService

## ⏭️ Следующие шаги
- [ ] Реализация модели `Movie`, `Hall`, `Seat`, `Session`
- [ ] Реализация `TicketService` и `TicketController`
- [ ] Создание `ApplicationRunner` (главное меню)
- [ ] Генерация отчетов через `ReportService`

## 🗂️ Структура проекта

```
 com.cinema
   ├── controller 
   │ ├── product 
   │ └── user 
   ├── model 
   │ ├── product 
   │ └── user 
   ├── service 
   │ ├── product 
   │ └── report 
   ├── util 
   │ └── exceptions 
   └── ApplicationRunner.java
```

## ✍️ Авторы
- **AlexH73** – [GitHub](https://github.com/AlexH73/cohortCinema)
