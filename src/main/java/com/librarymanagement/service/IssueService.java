package com.librarymanagement.service;

import com.librarymanagement.model.Issue;
import com.librarymanagement.repository.IssueRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class IssueService {

    private final IssueRepository issueRepository;


    // Fine per overdue day
    private static final double FINE_PER_DAY = 5.0;


    // Constructor Injection
    public IssueService(
            IssueRepository issueRepository) {

        this.issueRepository = issueRepository;
    }


    // ==========================================
    // GET ALL ISSUES
    // ==========================================

    public List<Issue> getAllIssues() {

        return issueRepository.getAllIssues();
    }


    // ==========================================
    // GET ISSUE BY ID
    // ==========================================

    public Issue getIssueById(int id) {

        return issueRepository.getIssueById(id);
    }


    // ==========================================
    // ISSUE A BOOK
    // ==========================================

    public Issue issueBook(Issue issue) {

        // Set issue date automatically
        if (issue.getIssueDate() == null) {

            issue.setIssueDate(
                    LocalDate.now()
            );
        }


        // Set status
        issue.setStatus("ISSUED");


        // Set fine to zero
        issue.setFine(0.0);


        // Reduce available book quantity
        int updated =
                issueRepository.decreaseBookQuantity(
                        issue.getBookId()
                );


        // If no book was available
        if (updated == 0) {

            throw new RuntimeException(
                    "Book is not available"
            );
        }


        // Save issue record
        issueRepository.issueBook(issue);


        return issue;
    }


    // ==========================================
    // RETURN A BOOK
    // ==========================================

    public Issue returnBook(int issueId) {

        // Find issue record
        Issue issue =
                issueRepository.getIssueById(
                        issueId
                );


        // Check issue exists
        if (issue == null) {

            throw new RuntimeException(
                    "Issue record not found"
            );
        }


        // Check if already returned
        if ("RETURNED".equals(
                issue.getStatus())) {

            throw new RuntimeException(
                    "Book has already been returned"
            );
        }


        // Current date
        LocalDate returnDate =
                LocalDate.now();


        // Calculate overdue days
        long overdueDays = 0;


        if (returnDate.isAfter(
                issue.getDueDate())) {

            overdueDays =
                    ChronoUnit.DAYS.between(
                            issue.getDueDate(),
                            returnDate
                    );
        }


        // Calculate fine
        double fine =
                overdueDays * FINE_PER_DAY;


        // Update issue record
        issueRepository.returnBook(
                issueId,
                returnDate,
                fine
        );


        // Increase available book quantity
        issueRepository.increaseBookQuantity(
                issue.getBookId()
        );


        // Update object
        issue.setReturnDate(
                returnDate
        );

        issue.setStatus(
                "RETURNED"
        );

        issue.setFine(
                fine
        );


        return issue;
    }


    // ==========================================
    // DELETE ISSUE
    // ==========================================

    public void deleteIssue(int id) {

        issueRepository.deleteIssue(id);
    }

}