# EAprojek

Spring Boot REST API for managing vehicle fuel consumption, emissions, manufacturers, and statistics.

This project was created as a school project for the Enterprise Applications course.

## Database

```powershell
docker compose up -d
```

The application connects to PostgreSQL on:

```text
jdbc:postgresql://localhost:5433/postgres
```

Stop the database:

```powershell
docker compose down
```

## Run The Application

```powershell
.\gradlew.bat bootRun
```

## API Documentation

```text
http://localhost:8090/swagger-ui/index.html
```
