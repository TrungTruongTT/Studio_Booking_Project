package com.example.demofacebook.Model;

import java.io.Serializable;
import java.util.List;

public class Studio implements Serializable {
    private int studioId;
    private int image;
    private String title;
    private int totalAlbum;
    private int rating;
    private String description;
    private List<Service> serviceList;

    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    public Studio(int studioId, int image, String title, int rating, List<Service> serviceList) {
        this.studioId = studioId;
        this.image = image;
        this.title = title;
        this.rating = rating;
        this.serviceList = serviceList;
    }

    public Studio(int studioId, int image, String title, int totalAlbum, int rating) {
        this.studioId = studioId;
        this.image = image;
        this.title = title;
        this.totalAlbum = totalAlbum;
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStudioId() {
        return studioId;
    }

    public void setStudioId(int studioId) {
        this.studioId = studioId;
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

    public int getTotalAlbum() {
        return totalAlbum;
    }

    public void setTotalAlbum(int totalAlbum) {
        this.totalAlbum = totalAlbum;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


}
