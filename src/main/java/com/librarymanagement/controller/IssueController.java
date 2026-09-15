package com.librarymanagement.controller;

import com.librarymanagement.model.Issue;
import com.librarymanagement.service.IssueService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
@CrossOrigin(origins = "*")
public class IssueController {

    private final IssueService issueService;

    // Constructor Injection
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    // ==========================================
    // GET ALL ISSUES
    // GET /api/issues
    // ==========================================

    @GetMapping
    public ResponseEntity<List<Issue>> getAllIssues() {

        List<Issue> issues = issueService.getAllIssues();

        return ResponseEntity.ok(issues);
    }


    // ==========================================
    // GET ISSUE BY ID
    // GET /api/issues/1
    // ==========================================

    @GetMapping("/{id}")
    public ResponseEntity<Issue> getIssueById(
            @PathVariable int id) {

        Issue issue = issueService.getIssueById(id);

        return ResponseEntity.ok(issue);
    }


    // ==========================================
    // ISSUE A BOOK
    // POST /api/issues
    // ==========================================

    @PostMapping
    public ResponseEntity<Issue> issueBook(
            @RequestBody Issue issue) {

        Issue savedIssue = issueService.issueBook(issue);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedIssue);
    }


    // ==========================================
    // RETURN A BOOK
    // PUT /api/issues/1/return
    // ==========================================

    @PutMapping("/{id}/return")
    public ResponseEntity<Issue> returnBook(
            @PathVariable int id) {

        Issue returnedIssue =
                issueService.returnBook(id);

        return ResponseEntity.ok(returnedIssue);
    }


    // ==========================================
    // DELETE ISSUE
    // DELETE /api/issues/1
    // ==========================================

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIssue(
            @PathVariable int id) {

        issueService.deleteIssue(id);

        return ResponseEntity.ok(
                "Issue record deleted successfully"
        );
    }
}