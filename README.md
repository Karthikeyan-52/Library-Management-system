# Library Management System

A Spring Boot and MySQL application for managing books, members, and book issue/return records.

## Features

- Book CRUD operations and search
- Member CRUD operations and search
- Book issue and return workflows
- Fine and availability tracking
- Static dashboard, books, members, and issue/return pages
- REST API backed by JDBC

## Prerequisites

Install the following software:

- Java 17 or newer
- Maven 3.9 or newer
- MySQL 8.0 or newer

Verify Java and Maven:

```powershell
java --version
mvn --version
```

This project is not an npm application. Do not use `npm run dev`.

## Database Setup

Start MySQL and create the database:

```sql
CREATE DATABASE library_management;
```

The application currently reads its connection settings from `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/library_management
spring.datasource.username=root
spring.datasource.password=your-password
```

Update the username and password to match your local MySQL installation. Create the `books`, `members`, and `issues` tables before using the API or pages.

Do not commit real database passwords. For production, move credentials to environment variables or an external configuration service.

## Run the Application

From the project directory:

```powershell
cd "D:\project\library intern project\library\library-management"
mvn spring-boot:run
```

Open the application in a browser:

- Dashboard: http://localhost:8080/
- Books: http://localhost:8080/books.html
- Members: http://localhost:8080/members.html
- Issue/Return: http://localhost:8080/issue.html

Stop the application with `Ctrl+C`.

## Build and Test

Run the test suite:

```powershell
mvn test
```

Build the executable JAR:

```powershell
mvn clean package
```

Run the packaged application:

```powershell
java -jar target/library-management-0.0.1-SNAPSHOT.jar
```

## REST API

### Books

- `GET /api/books`
- `GET /api/books/{id}`
- `POST /api/books`
- `PUT /api/books/{id}`
- `DELETE /api/books/{id}`
- `GET /api/books/search?keyword=java`

### Members

- `GET /api/members`
- `GET /api/members/{id}`
- `POST /api/members`
- `PUT /api/members/{id}`
- `DELETE /api/members/{id}`
- `GET /api/members/search?keyword=alex`

### Issues

- `GET /api/issues`
- `GET /api/issues/{id}`
- `POST /api/issues`
- `PUT /api/issues/{id}/return`
- `DELETE /api/issues/{id}`

## Project Structure

```text
src/main/java/com/librarymanagement/
  controller/       REST controllers
  model/            Book, Member, and Issue models
  repository/       JDBC data access
  service/          Business logic

src/main/resources/
  application.properties
  static/           HTML, CSS, and JavaScript frontend
```

See [REQUIREMENTS.md](REQUIREMENTS.md) for the complete functional and non-functional requirements.
