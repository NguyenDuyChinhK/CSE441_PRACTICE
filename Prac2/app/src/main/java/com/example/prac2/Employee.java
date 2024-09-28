package com.example.prac2;
public class Employee {

    private String staffId;
    private String fullName;
    private String birthDay;
    private float salary;


    public Employee(String staffId, String fullName, String birthDay, float salary) {
        this.staffId = staffId;
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.salary = salary;
    }


    public String getStaffId() {
        return staffId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public float getSalary() {
        return salary;
    }
}
