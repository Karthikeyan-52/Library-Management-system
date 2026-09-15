package com.librarymanagement.service;

import com.librarymanagement.model.Book;
import com.librarymanagement.repository.BookRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;


    // Constructor Injection
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    // ==========================================
    // GET ALL BOOKS
    // ==========================================

    public List<Book> getAllBooks() {

        return bookRepository.getAllBooks();
    }


    // ==========================================
    // GET BOOK BY ID
    // ==========================================

    public Book getBookById(int id) {

        return bookRepository.getBookById(id);
    }


    // ==========================================
    // ADD NEW BOOK
    // ==========================================

    public Book addBook(Book book) {

        // If available quantity is not provided,
        // set it equal to total quantity

        if (book.getAvailableQuantity() == 0) {

            book.setAvailableQuantity(
                    book.getQuantity()
            );
        }

        bookRepository.addBook(book);

        return book;
    }


    // ==========================================
    // UPDATE BOOK
    // ==========================================

    public Book updateBook(
            int id,
            Book book) {

        bookRepository.updateBook(
                id,
                book
        );

        book.setBookId(id);

        return book;
    }


    // ==========================================
    // DELETE BOOK
    // ==========================================

    public void deleteBook(int id) {

        bookRepository.deleteBook(id);
    }


    // ==========================================
    // SEARCH BOOKS
    // ==========================================

    public List<Book> searchBooks(
            String keyword) {

        return bookRepository.searchBooks(
                keyword
        );
    }

}