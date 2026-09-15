package com.librarymanagement.repository;

import com.librarymanagement.model.Member;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    private final JdbcTemplate jdbcTemplate;


    // Constructor Injection
    public MemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    // ==========================================
    // ROW MAPPER
    // Converts database row into Member object
    // ==========================================

        @NonNull
        private final RowMapper<Member> rowMapper = (rs, rowNum) -> {

        Member member = new Member();

        member.setMemberId(
                rs.getInt("member_id")
        );

        member.setName(
                rs.getString("name")
        );

        member.setEmail(
                rs.getString("email")
        );

        member.setPhone(
                rs.getString("phone")
        );

        member.setStatus(
                rs.getString("status")
        );

        return member;
    };


    // ==========================================
    // GET ALL MEMBERS
    // ==========================================

    public List<Member> getAllMembers() {

        String sql =
                "SELECT * FROM members";

        return jdbcTemplate.query(
                sql,
                rowMapper
        );
    }


    // ==========================================
    // GET MEMBER BY ID
    // ==========================================

    public Member getMemberById(int id) {

        String sql =
                "SELECT * FROM members WHERE member_id = ?";

        List<Member> members =
                jdbcTemplate.query(
                        sql,
                        rowMapper,
                        id
                );

        if (members.isEmpty()) {
            return null;
        }

        return members.get(0);
    }


    // ==========================================
    // ADD NEW MEMBER
    // ==========================================

    public int addMember(Member member) {

        String sql =
                """
                INSERT INTO members
                (name, email, phone, status)
                VALUES (?, ?, ?, ?)
                """;

        return jdbcTemplate.update(
                sql,
                member.getName(),
                member.getEmail(),
                member.getPhone(),
                member.getStatus()
        );
    }


    // ==========================================
    // UPDATE MEMBER
    // ==========================================

    public int updateMember(
            int id,
            Member member) {

        String sql =
                """
                UPDATE members
                SET name = ?,
                    email = ?,
                    phone = ?,
                    status = ?
                WHERE member_id = ?
                """;

        return jdbcTemplate.update(
                sql,
                member.getName(),
                member.getEmail(),
                member.getPhone(),
                member.getStatus(),
                id
        );
    }


    // ==========================================
    // DELETE MEMBER
    // ==========================================

    public int deleteMember(int id) {

        String sql =
                "DELETE FROM members WHERE member_id = ?";

        return jdbcTemplate.update(
                sql,
                id
        );
    }


    // ==========================================
    // SEARCH MEMBERS
    // Search by name, email, or phone
    // ==========================================

    public List<Member> searchMembers(
            String keyword) {

        String sql =
                """
                SELECT * FROM members
                WHERE name LIKE ?
                   OR email LIKE ?
                   OR phone LIKE ?
                """;

        String searchKeyword =
                "%" + keyword + "%";

        return jdbcTemplate.query(
                sql,
                rowMapper,
                searchKeyword,
                searchKeyword,
                searchKeyword
        );
    }

}