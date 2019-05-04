package com.example.complaintmanagementsystem.Models.User;

public class Professor{
    private String faculty;
    private String designation;
    private String officeNo;

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getOfficeNo() {
        return officeNo;
    }

    public void setOfficeNo(String officeNo) {
        this.officeNo = officeNo;
    }

    public Professor(String faculty, String designation, String officeNo){
        this.faculty = faculty;
        this.designation = designation;
        this.officeNo = officeNo;
    }

}
