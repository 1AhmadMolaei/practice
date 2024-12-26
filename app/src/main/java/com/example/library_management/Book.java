package com.example.library_management;

public class Book {
    private String id;
    private String title;
    private String publisherName;

    public Book() {
        // Default constructor
    }

    public Book(String id, String title, String publisherName) {
        this.id = id;
        this.title = title;
        this.publisherName = publisherName;
    }

    public Book(String title, String publisherName) {

        this.title = title;
        this.publisherName = publisherName;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
}

