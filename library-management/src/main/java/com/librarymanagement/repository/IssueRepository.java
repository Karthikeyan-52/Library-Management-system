package com.librarymanagement.repository;

import com.librarymanagement.model.Issue;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class IssueRepository {

    private final JdbcTemplate jdbcTemplate;


    // Constructor Injection
    public IssueRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    // ==========================================
    // ROW MAPPER
    // Converts database row into Issue object
    // ==========================================

        @NonNull
        private final RowMapper<Issue> rowMapper = (rs, rowNum) -> {

        Issue issue = new Issue();

        issue.setIssueId(
                rs.getInt("issue_id")
        );

        issue.setBookId(
                rs.getInt("book_id")
        );

        issue.setMemberId(
                rs.getInt("member_id")
        );

        Date issueDate =
                rs.getDate("issue_date");

        if (issueDate != null) {
            issue.setIssueDate(
                    issueDate.toLocalDate()
            );
        }

        Date dueDate =
                rs.getDate("due_date");

        if (dueDate != null) {
            issue.setDueDate(
                    dueDate.toLocalDate()
            );
        }

        Date returnDate =
                rs.getDate("return_date");

        if (returnDate != null) {
            issue.setReturnDate(
                    returnDate.toLocalDate()
            );
        }

        issue.setStatus(
                rs.getString("status")
        );

        issue.setFine(
                rs.getDouble("fine")
        );

        return issue;
    };


    // ==========================================
    // GET ALL ISSUES
    // ==========================================

    public List<Issue> getAllIssues() {

        String sql =
                "SELECT * FROM issues";

        return jdbcTemplate.query(
                sql,
                rowMapper
        );
    }


    // ==========================================
    // GET ISSUE BY ID
    // ==========================================

    public Issue getIssueById(int id) {

        String sql =
                "SELECT * FROM issues WHERE issue_id = ?";

        List<Issue> issues =
                jdbcTemplate.query(
                        sql,
                        rowMapper,
                        id
                );

        if (issues.isEmpty()) {
            return null;
        }

        return issues.get(0);
    }


    // ==========================================
    // ISSUE A BOOK
    // ==========================================

    public int issueBook(Issue issue) {

        String sql =
                """
                INSERT INTO issues
                (book_id, member_id, issue_date,
                 due_date, status, fine)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        return jdbcTemplate.update(
                sql,
                issue.getBookId(),
                issue.getMemberId(),
                issue.getIssueDate(),
                issue.getDueDate(),
                "ISSUED",
                0.0
        );
    }


    // ==========================================
    // DECREASE AVAILABLE BOOK QUANTITY
    // ==========================================

    public int decreaseBookQuantity(
            int bookId) {

        String sql =
                """
                UPDATE books
                SET available_quantity =
                    available_quantity - 1
                WHERE book_id = ?
                AND available_quantity > 0
                """;

        return jdbcTemplate.update(
                sql,
                bookId
        );
    }


    // ==========================================
    // RETURN A BOOK
    // ==========================================

    public int returnBook(
            int issueId,
            java.time.LocalDate returnDate,
            double fine) {

        String sql =
                """
                UPDATE issues
                SET return_date = ?,
                    status = 'RETURNED',
                    fine = ?
                WHERE issue_id = ?
                """;

        return jdbcTemplate.update(
                sql,
                returnDate,
                fine,
                issueId
        );
    }


    // ==========================================
    // INCREASE AVAILABLE BOOK QUANTITY
    // ==========================================

    public int increaseBookQuantity(
            int bookId) {

        String sql =
                """
                UPDATE books
                SET available_quantity =
                    available_quantity + 1
                WHERE book_id = ?
                """;

        return jdbcTemplate.update(
                sql,
                bookId
        );
    }


    // ==========================================
    // DELETE ISSUE
    // ==========================================

    public int deleteIssue(int id) {

        String sql =
                "DELETE FROM issues WHERE issue_id = ?";

        return jdbcTemplate.update(
                sql,
                id
        );
    }

}