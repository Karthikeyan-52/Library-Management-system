package com.librarymanagement.controller;

import com.librarymanagement.model.Book;
import com.librarymanagement.service.BookService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class BookController {

    private final BookService bookService;

    // Constructor Injection
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // ==========================================
    // GET ALL BOOKS
    // GET http://localhost:8080/api/books
    // ==========================================

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {

        List<Book> books = bookService.getAllBooks();

        return ResponseEntity.ok(books);
    }


    // ==========================================
    // GET BOOK BY ID
    // GET http://localhost:8080/api/books/1
    // ==========================================

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(
            @PathVariable int id) {

        Book book = bookService.getBookById(id);

        return ResponseEntity.ok(book);
    }


    // ==========================================
    // ADD NEW BOOK
    // POST http://localhost:8080/api/books
    // ==========================================

    @PostMapping
    public ResponseEntity<Book> addBook(
            @RequestBody Book book) {

        Book savedBook = bookService.addBook(book);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedBook);
    }


    // ==========================================
    // UPDATE BOOK
    // PUT http://localhost:8080/api/books/1
    // ==========================================

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable int id,
            @RequestBody Book book) {

        Book updatedBook =
                bookService.updateBook(id, book);

        return ResponseEntity.ok(updatedBook);
    }


    // ==========================================
    // DELETE BOOK
    // DELETE http://localhost:8080/api/books/1
    // ==========================================

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(
            @PathVariable int id) {

        bookService.deleteBook(id);

        return ResponseEntity.ok(
                "Book deleted successfully"
        );
    }


    // ==========================================
    // SEARCH BOOKS
    // GET /api/books/search?keyword=java
    // ==========================================

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(
            @RequestParam String keyword) {

        List<Book> books =
                bookService.searchBooks(keyword);

        return ResponseEntity.ok(books);
    }
}