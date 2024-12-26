package com.example.library_management;

public class BookAuthor {
    private long bookId;
    private String authorName;

    public BookAuthor() {

    }

    // Getters and setters for book ID and author name
    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
