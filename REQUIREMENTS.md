# Library Management System Requirements

## 1. Project Overview

The Library Management System is a Spring Boot web application for managing books, library members, and book issue/return records.

## 2. Technology Requirements

- Java 17 or newer
- Spring Boot 3.5.0
- Maven 3.9 or newer
- MySQL 8.0 or newer
- Modern web browser

## 3. Functional Requirements

### 3.1 Book Management

- The system shall display all books.
- The system shall display a book by its ID.
- The system shall add a book with title, author, ISBN, category, quantity, and available quantity.
- The system shall update book information.
- The system shall delete a book.
- The system shall search books by title, author, ISBN, or category.

### 3.2 Member Management

- The system shall display all members.
- The system shall display a member by its ID.
- The system shall add a member with name, email, phone, and status.
- The system shall update member information.
- The system shall delete a member.
- The system shall search members by name, email, or phone.

### 3.3 Issue and Return Management

- The system shall display all issue records.
- The system shall display an issue record by its ID.
- The system shall issue a book to a member.
- The system shall record the issue date, due date, return date, status, and fine.
- The system shall return an issued book.
- The system shall update book availability when a book is issued or returned.
- The system shall delete an issue record.

### 3.4 Web Interface

- The system shall provide a dashboard page.
- The system shall provide pages for books, members, and issue/return operations.
- The web pages shall communicate with the REST API using JSON.

## 4. REST API Requirements

### Books

- `GET /api/books`
- `GET /api/books/{id}`
- `POST /api/books`
- `PUT /api/books/{id}`
- `DELETE /api/books/{id}`
- `GET /api/books/search?keyword={keyword}`

### Members

- `GET /api/members`
- `GET /api/members/{id}`
- `POST /api/members`
- `PUT /api/members/{id}`
- `DELETE /api/members/{id}`
- `GET /api/members/search?keyword={keyword}`

### Issues

- `GET /api/issues`
- `GET /api/issues/{id}`
- `POST /api/issues`
- `PUT /api/issues/{id}/return`
- `DELETE /api/issues/{id}`

## 5. Database Requirements

The application requires a MySQL database named `library_management` with tables for:

- `books`
- `members`
- `issues`

The `issues` table should reference the relevant book and member records using foreign keys. The database user must have permission to read and modify these tables.

## 6. Non-Functional Requirements

- The application shall run on port `8080` by default.
- Database queries shall use parameterized JDBC statements.
- The application shall expose JSON REST responses.
- The application shall support cross-origin requests for the current frontend integration.
- The application shall be buildable and testable using Maven.
- Database passwords shall be externalized before production deployment and shall not be committed to source control.

## 7. Acceptance Criteria

- The application starts successfully after MySQL and the required schema are available.
- The dashboard and management pages load at `http://localhost:8080`.
- Book, member, and issue API requests return successful responses for valid data.
- Issuing and returning a book keeps availability data consistent.
- `mvn test` completes successfully.
