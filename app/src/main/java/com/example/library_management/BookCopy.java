package com.example.library_management;

public class BookCopy {
    private String bookId;
    private String branchId;
    private String accessNo;

    public BookCopy() {
        // Default constructor
    }

    // Getters and setters for book ID, branch ID, and access number
    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getAccessNo() {
        return accessNo;
    }

    public void setAccessNo(String accessNo) {
        this.accessNo = accessNo;
    }
}
