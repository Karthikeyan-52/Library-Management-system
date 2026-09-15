package com.librarymanagement.model;

public class Book {

    private int bookId;

    private String title;

    private String author;

    private String isbn;

    private String category;

    private int quantity;

    private int availableQuantity;


    // Default Constructor
    public Book() {

    }


    // Parameterized Constructor
    public Book(
            int bookId,
            String title,
            String author,
            String isbn,
            String category,
            int quantity,
            int availableQuantity) {

        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.quantity = quantity;
        this.availableQuantity = availableQuantity;
    }


    // Getter and Setter for bookId

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }


    // Getter and Setter for title

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    // Getter and Setter for author

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    // Getter and Setter for ISBN

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    // Getter and Setter for category

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    // Getter and Setter for quantity

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    // Getter and Setter for availableQuantity

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }


    // toString Method

    @Override
    public String toString() {

        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                ", availableQuantity=" + availableQuantity +
                '}';
    }
}