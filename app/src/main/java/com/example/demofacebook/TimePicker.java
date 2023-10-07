package com.example.demofacebook;

public class TimePicker {
    private int id;
    private String studioName;
    private String studioImage;
    private String studioDescription;
    private int Price;
    private String Time;


    public TimePicker(int id, String studioName, String studioImage, String studioDescription, int price, String time) {
        this.id = id;
        this.studioName = studioName;
        this.studioImage = studioImage;
        this.studioDescription = studioDescription;
        Price = price;
        Time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudioName() {
        return studioName;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName;
    }

    public String getStudioImage() {
        return studioImage;
    }

    public void setStudioImage(String studioImage) {
        this.studioImage = studioImage;
    }

    public String getStudioDescription() {
        return studioDescription;
    }

    public void setStudioDescription(String studioDescription) {
        this.studioDescription = studioDescription;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    @Override
    public String toString() {
        return "TimePicker{" +
                "id='" + id + '\'' +
                ", studioName='" + studioName + '\'' +
                ", studioImage='" + studioImage + '\'' +
                ", studioDescription='" + studioDescription + '\'' +
                ", Price='" + Price + '\'' +
                ", Time='" + Time + '\'' +
                '}';
    }
}
