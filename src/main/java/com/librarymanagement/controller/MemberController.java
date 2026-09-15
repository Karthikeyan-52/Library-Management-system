package com.librarymanagement.controller;

import com.librarymanagement.model.Member;
import com.librarymanagement.service.MemberService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins = "*")
public class MemberController {

    private final MemberService memberService;

    // Constructor Injection
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    // ==========================================
    // GET ALL MEMBERS
    // GET /api/members
    // ==========================================

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {

        List<Member> members =
                memberService.getAllMembers();

        return ResponseEntity.ok(members);
    }


    // ==========================================
    // GET MEMBER BY ID
    // GET /api/members/1
    // ==========================================

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(
            @PathVariable int id) {

        Member member =
                memberService.getMemberById(id);

        return ResponseEntity.ok(member);
    }


    // ==========================================
    // ADD NEW MEMBER
    // POST /api/members
    // ==========================================

    @PostMapping
    public ResponseEntity<Member> addMember(
            @RequestBody Member member) {

        Member savedMember =
                memberService.addMember(member);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedMember);
    }


    // ==========================================
    // UPDATE MEMBER
    // PUT /api/members/1
    // ==========================================

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(
            @PathVariable int id,
            @RequestBody Member member) {

        Member updatedMember =
                memberService.updateMember(id, member);

        return ResponseEntity.ok(updatedMember);
    }


    // ==========================================
    // DELETE MEMBER
    // DELETE /api/members/1
    // ==========================================

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(
            @PathVariable int id) {

        memberService.deleteMember(id);

        return ResponseEntity.ok(
                "Member deleted successfully"
        );
    }


    // ==========================================
    // SEARCH MEMBERS
    // GET /api/members/search?keyword=karthi
    // ==========================================

    @GetMapping("/search")
    public ResponseEntity<List<Member>> searchMembers(
            @RequestParam String keyword) {

        List<Member> members =
                memberService.searchMembers(keyword);

        return ResponseEntity.ok(members);
    }
}