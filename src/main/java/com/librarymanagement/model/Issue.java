package com.librarymanagement.model;

import java.time.LocalDate;

public class Issue {

    private int issueId;

    private int bookId;

    private int memberId;

    private LocalDate issueDate;

    private LocalDate dueDate;

    private LocalDate returnDate;

    private String status;

    private double fine;


    // Default Constructor
    public Issue() {

    }


    // Parameterized Constructor
    public Issue(
            int issueId,
            int bookId,
            int memberId,
            LocalDate issueDate,
            LocalDate dueDate,
            LocalDate returnDate,
            String status,
            double fine) {

        this.issueId = issueId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
        this.fine = fine;
    }


    // Getter and Setter for issueId

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }


    // Getter and Setter for bookId

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }


    // Getter and Setter for memberId

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }


    // Getter and Setter for issueDate

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }


    // Getter and Setter for dueDate

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }


    // Getter and Setter for returnDate

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }


    // Getter and Setter for status

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    // Getter and Setter for fine

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }


    // toString Method

    @Override
    public String toString() {

        return "Issue{" +
                "issueId=" + issueId +
                ", bookId=" + bookId +
                ", memberId=" + memberId +
                ", issueDate=" + issueDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                ", status='" + status + '\'' +
                ", fine=" + fine +
                '}';
    }
}