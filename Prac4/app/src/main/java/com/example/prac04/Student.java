package com.example.prac04;

import java.io.Serializable;

public class Student implements Serializable {
        private String id;
        private String email;
        private String fullName;
        private String gender;
        private String major;
        private double gpa;
        private String birthDay;
        private String address;
        private int year;

    // Constructor
    public Student(String id, String email, String fullName, String gender, String major, double gpa, String birthDay, String address, int year) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.gender = gender;
        this.major = major;
        this.gpa = gpa;
        this.birthDay = birthDay;
        this.address = address;
        this.year = year;
    }

    // Getter
    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getGender() {
        return gender;
    }

    public String getMajor() {
        return major;
    }

    public double getGpa() {
        return gpa;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public String getAddress() {
        return address;
    }

    public int getYear() {
        return year;
    }

    public String getFirstName() {
        String[] names = fullName.split(" "); // Tách tên
        return names[names.length - 1]; // Giả sử tên là phần cuối
    }

    public String getLastName() {
        String[] names = fullName.split(" ");
        if (names.length > 1) {
            return names[0]; // Giả sử họ là phần đầu tiên
        }
        return ""; // Nếu không có họ
    }
}
