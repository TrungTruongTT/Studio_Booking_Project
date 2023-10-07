package com.example.demofacebook;

import androidx.annotation.NonNull;

public class DatePicker {
    private int id;
    private String Date;
    private String Month;

    public DatePicker(int id, String date, String month) {
        this.id = id;
        Date = date;
        Month = month;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    @Override
    public String toString() {
        return "DatePicker{" +
                "id='" + id + '\'' +
                ", Date='" + Date + '\'' +
                ", Month='" + Month + '\'' +
                '}';
    }
}
