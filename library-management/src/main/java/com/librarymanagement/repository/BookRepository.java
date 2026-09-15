package com.librarymanagement.repository;

import com.librarymanagement.model.Book;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {

    private final JdbcTemplate jdbcTemplate;

    // Constructor Injection
    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    // ==========================================
    // ROW MAPPER
    // Converts database row into Book object
    // ==========================================
    
        @NonNull
        private final RowMapper<Book> rowMapper = (rs, rowNum) -> {

        Book book = new Book();

        book.setBookId(
                rs.getInt("book_id")
        );

        book.setTitle(
                rs.getString("title")
        );

        book.setAuthor(
                rs.getString("author")
        );

        book.setIsbn(
                rs.getString("isbn")
        );

        book.setCategory(
                rs.getString("category")
        );

        book.setQuantity(
                rs.getInt("quantity")
        );

        book.setAvailableQuantity(
                rs.getInt("available_quantity")
        );

        return book;
    };


    // ==========================================
    // GET ALL BOOKS
    // ==========================================

    public List<Book> getAllBooks() {

        String sql =
                "SELECT * FROM books";

        return jdbcTemplate.query(
                sql,
                rowMapper
        );
    }


    // ==========================================
    // GET BOOK BY ID
    // ==========================================

    public Book getBookById(int id) {

        String sql =
                "SELECT * FROM books WHERE book_id = ?";

        List<Book> books =
                jdbcTemplate.query(
                        sql,
                        rowMapper,
                        id
                );

        if (books.isEmpty()) {
            return null;
        }

        return books.get(0);
    }


    // ==========================================
    // ADD NEW BOOK
    // ==========================================

    public int addBook(Book book) {

        String sql =
                """
                INSERT INTO books
                (title, author, isbn, category,
                 quantity, available_quantity)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        return jdbcTemplate.update(
                sql,
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getCategory(),
                book.getQuantity(),
                book.getAvailableQuantity()
        );
    }


    // ==========================================
    // UPDATE BOOK
    // ==========================================

    public int updateBook(
            int id,
            Book book) {

        String sql =
                """
                UPDATE books
                SET title = ?,
                    author = ?,
                    isbn = ?,
                    category = ?,
                    quantity = ?,
                    available_quantity = ?
                WHERE book_id = ?
                """;

        return jdbcTemplate.update(
                sql,
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getCategory(),
                book.getQuantity(),
                book.getAvailableQuantity(),
                id
        );
    }


    // ==========================================
    // DELETE BOOK
    // ==========================================

    public int deleteBook(int id) {

        String sql =
                "DELETE FROM books WHERE book_id = ?";

        return jdbcTemplate.update(
                sql,
                id
        );
    }


    // ==========================================
    // SEARCH BOOKS
    // Search by title, author, ISBN, category
    // ==========================================

    public List<Book> searchBooks(
            String keyword) {

        String sql =
                """
                SELECT * FROM books
                WHERE title LIKE ?
                   OR author LIKE ?
                   OR isbn LIKE ?
                   OR category LIKE ?
                """;

        String searchKeyword =
                "%" + keyword + "%";

        return jdbcTemplate.query(
                sql,
                rowMapper,
                searchKeyword,
                searchKeyword,
                searchKeyword,
                searchKeyword
        );
    }

}