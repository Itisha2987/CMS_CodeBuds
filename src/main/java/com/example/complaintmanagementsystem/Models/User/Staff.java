package com.example.complaintmanagementsystem.Models.User;

public class Staff{
    private String department;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    private String designation;

    public Staff(String department, String designation){
        this.department = department;
        this.designation = designation;
    }
}
