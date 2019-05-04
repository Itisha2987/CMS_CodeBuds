package com.example.complaintmanagementsystem.Models.User;

public class Student {
    private String hostelNo;
    private int roomNo;
    private Boolean isSingleRoom;
    private String faculty;
    private int regno;

    public String getHostelNo() {
        return hostelNo;
    }

    public void setHostelNo(String hostelNo) {
        this.hostelNo = hostelNo;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public Boolean getSingleRoom() {
        return isSingleRoom;
    }

    public void setSingleRoom(Boolean singleRoom) {
        isSingleRoom = singleRoom;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public int getRegno() {
        return regno;
    }

    public void setRegno(int regno) {
        this.regno = regno;
    }

    public Student(String hostelNo, int roomNo, String faculty, int regno, Boolean isSingleRoom)
    {
        this.hostelNo = hostelNo;
        this.roomNo = roomNo;
        this.faculty = faculty;
        this.regno = regno;
        this.isSingleRoom = isSingleRoom;
    }



}
