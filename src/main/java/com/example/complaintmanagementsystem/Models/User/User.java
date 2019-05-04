package com.example.complaintmanagementsystem.Models.User;

public class User {
    private String name;
    private String email;
    private int role;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public User(String name, String email, int role, String type){
        this.name = name;
        this.email = email;
        this.role = role;
        this.type=type;
    }

}
