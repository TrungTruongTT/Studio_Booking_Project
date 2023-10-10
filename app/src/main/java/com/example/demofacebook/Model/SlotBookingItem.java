package com.example.demofacebook.Model;

public class SlotBookingItem {
    private int id;
    private String time;

    public SlotBookingItem(int id, String time) {
        this.id = id;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "BookingItem{" +
                "id='" + id + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
