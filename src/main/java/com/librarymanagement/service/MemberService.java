package com.librarymanagement.service;

import com.librarymanagement.model.Member;
import com.librarymanagement.repository.MemberRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;


    // Constructor Injection
    public MemberService(
            MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
    }


    // ==========================================
    // GET ALL MEMBERS
    // ==========================================

    public List<Member> getAllMembers() {

        return memberRepository.getAllMembers();
    }


    // ==========================================
    // GET MEMBER BY ID
    // ==========================================

    public Member getMemberById(int id) {

        return memberRepository.getMemberById(id);
    }


    // ==========================================
    // ADD NEW MEMBER
    // ==========================================

    public Member addMember(Member member) {

        // Set default status
        if (member.getStatus() == null ||
                member.getStatus().isEmpty()) {

            member.setStatus("ACTIVE");
        }


        // Save member
        memberRepository.addMember(member);


        return member;
    }


    // ==========================================
    // UPDATE MEMBER
    // ==========================================

    public Member updateMember(
            int id,
            Member member) {

        memberRepository.updateMember(
                id,
                member
        );


        // Set ID in returned object
        member.setMemberId(id);


        return member;
    }


    // ==========================================
    // DELETE MEMBER
    // ==========================================

    public void deleteMember(int id) {

        memberRepository.deleteMember(id);
    }


    // ==========================================
    // SEARCH MEMBERS
    // ==========================================

    public List<Member> searchMembers(
            String keyword) {

        return memberRepository.searchMembers(
                keyword
        );
    }

}