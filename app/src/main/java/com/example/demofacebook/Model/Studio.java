package com.example.demofacebook.Model;

import java.io.Serializable;

public class Studio implements Serializable {
    private int image;
    private String title;
    private String description;
    private int price;
    private int rating;

    public Studio(int image, String title, String description, int price,  int rating) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.price = price;
        this.rating = rating;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
