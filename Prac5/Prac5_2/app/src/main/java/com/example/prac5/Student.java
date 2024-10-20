package com.example.prac5;

public class Student {
    private String hoten, mssv, lop;
    private double diem;

    public Student() {
    }

    public Student(String mssv, String hoten, String lop, double diem) {
        this.mssv = mssv;
        this.hoten = hoten;
        this.lop = lop;
        this.diem = diem;
    }


    public double getDiem() {
        return diem;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }
}
