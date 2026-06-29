# EAprojek

Spring Boot REST API for managing vehicle fuel consumption, emissions, manufacturers, and statistics.

This project was created as a school project for the Enterprise Applications course.

## Technologies

- Java 17
- Spring Boot 3.3
- Spring Web
- Spring Data JPA
- PostgreSQL
- Liquibase
- Gradle
- Docker Compose
- Swagger / OpenAPI

## Features

- CRUD operations for vehicle fuel data
- CRUD operations for vehicle manufacturers
- Statistics for fuel consumption, emissions, vehicle classes, and manufacturers
- CSV data import on application startup
- Database migrations with Liquibase
- API documentation through Swagger UI

## Database

Start PostgreSQL:

```powershell
docker compose up -d
```

The application connects to PostgreSQL on:

```text
jdbc:postgresql://localhost:5433/postgres
```

Default database credentials:

```text
username: postgres
password: 123
database: postgres
```

Stop PostgreSQL:

```powershell
docker compose down
```

## Run The Application

Start the application with Gradle:

```powershell
.\gradlew.bat bootRun
```

The API runs on:

```text
http://localhost:8090
```

## API Documentation

Swagger UI is available at:

```text
http://localhost:8090/swagger-ui/index.html
```

## Main Endpoints

```text
GET    /fuelData
POST   /fuelData
GET    /fuelData/{id}
PUT    /fuelData/{id}
DELETE /fuelData/{id}

GET    /manufacturers
POST   /manufacturers
GET    /manufacturers/{id}
PUT    /manufacturers/{id}
DELETE /manufacturers/{id}

GET    /statistics
```

## Tests

Run tests:

```powershell
.\gradlew.bat test
```
