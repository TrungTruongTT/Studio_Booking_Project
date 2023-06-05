package com.example.demofacebook.Model;

import java.io.Serializable;

public class Studio implements Serializable {
    private int image;
    private String title;
    private String description;
    private String price;
    private String rating;

    public Studio(int image, String title, String description, String price,  String rating) {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
