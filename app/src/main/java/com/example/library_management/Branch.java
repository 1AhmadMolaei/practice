package com.example.library_management;

public class Branch {
    private String id;
    private String name;
    private String address;

    public Branch() {
        // Default constructor
    }

    public Branch(String name, String address) {
        this.id = null;
        this.name = name;
        this.address = address;
    }

    // Getters and setters for branch ID, name, and address
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
