# 🎓 Learning Platform (Учебная платформа)

Полноценное веб-приложение на **Spring Boot** для управления учебными курсами, реализующее паттерны ORM (Hibernate/JPA), REST API и интеграционное тестирование.

## 🛠 Стек технологий
- **Java 17**, **Spring Boot 3.2** (Web, Data JPA, Validation)
- **PostgreSQL** (Production), **H2** (Testing)
- **Maven** (сборка), **Docker** & **Docker Compose** (контейнеризация)
- **JUnit 5**, **Spring Boot Test** (интеграционные тесты)

## 🚀 Быстрый старт

### Вариант 1: Docker Compose (екомендуется)
Самый простой способ поднять приложение и базу данных одной командой:
```
docker-compose up --buildриложение будет доступно по адресу: http://localhost:8080
Вариант 2: окальный запуск (Maven)
Убедитесь, что у вас установлен PostgreSQL и создана база данных learning_platform.
Настройте подключение в src/main/resources/application-dev.yml (или через переменные окружения DB_URL, DB_USERNAME, DB_PASSWORD).
Запустите проект:mvn spring-boot:run📡 REST API ндпоинты
Курсы и бучение
Метод
URL
Описание
POST
/api/courses
Создать новый курс
GET
/api/courses
Получить список всех курсов
GET
/api/courses/{id}
Получить курс по ID
PUT
/api/courses/{id}
Обновить курс
DELETE
/api/courses/{id}
Удалить курс
POST
/api/courses/{id}/modules
Добавить модуль к курсу
Запись на курсы (Enrollments)
етод
URL
Описание
POST
/api/enrollments
Записать студента на курс
DELETE
/api/enrollments
Отписать студента от курса
GET
/api/enrollments/student/{id}
Курсы студента
GET
/api/enrollments/course/{id}
Студенты курса
Задания и ешения
Метод
URL
писание
POST
/api/assignments
Создать задание
POST
/api/submissions
Сдать решение
PUT
/api/submissions/{id}/grade
Оценить решение
Тесты (Quizzes)
Метод
URL
Описание
POST
/api/quizzes
Создать тест
POST
/api/quizzes/{id}/questions
Добавить вопрос
🧪 Тестирование
Проект покрыт интеграционными тестами. 
Для запуска:
Примечание: Тесты используют встроенную  H2 (профиль test), поэтому наличие PostgreSQL не требуется для прогона тестов.
🏗 Архитектура
Проект разделён на слои (Layered Architecture):
entity: JPA-сущности (17 моделей с настройкой Lazy Loading).
repository: Spring Data JPA интерфейсы.
service: изнес-логика и транзакции.
web: REST-контроллеры.
exception: Глобальная обработка ошибок (@ControllerAdvice).
📝 Лицензия
Учебный проект.