package com.example.library_management;

public class Member {
    private String cardNo;
    private String name;
    private String address;
    private String phone;
    private float unpaidDues;

    public Member() {
        // Default constructor
    }

    public Member(String name, String address, String phone) {

        this.name = name;
        this.address = address;
        this.phone = phone;

    }

    // Getters and setters


    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getUnpaidDues() {
        return unpaidDues;
    }


}
