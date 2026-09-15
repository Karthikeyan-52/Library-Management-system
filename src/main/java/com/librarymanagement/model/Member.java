package com.librarymanagement.model;

public class Member {

    private int memberId;

    private String name;

    private String email;

    private String phone;

    private String status;


    // Default Constructor
    public Member() {

    }


    // Parameterized Constructor
    public Member(
            int memberId,
            String name,
            String email,
            String phone,
            String status) {

        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }


    // Getter and Setter for memberId

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }


    // Getter and Setter for name

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    // Getter and Setter for email

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    // Getter and Setter for phone

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    // Getter and Setter for status

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    // toString Method

    @Override
    public String toString() {

        return "Member{" +
                "memberId=" + memberId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}