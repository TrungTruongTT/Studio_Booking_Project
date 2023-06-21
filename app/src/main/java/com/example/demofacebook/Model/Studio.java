package com.example.demofacebook.Model;

import java.io.Serializable;
import java.util.List;

public class Studio implements Serializable {
    private int studioId;
    private int image;
    private String title;
    private int totalAlbum;
    private int rating;

    private String titleDescription;
    private String description;
    private List<Service> serviceList;


    public Studio(int studioId, int image, String title, int totalAlbum, int rating, String description, List<Service> serviceList) {
        this.studioId = studioId;
        this.image = image;
        this.title = title;
        this.totalAlbum = totalAlbum;
        this.rating = rating;
        this.description = description;
        this.serviceList = serviceList;
    }


    public Studio(int studioId, int image, String title, int totalAlbum, int rating, String titleDescription, String description) {
        this.studioId = studioId;
        this.image = image;
        this.title = title;
        this.totalAlbum = totalAlbum;
        this.rating = rating;
        this.titleDescription = titleDescription;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Studio{" +
                "studioId=" + studioId +
                ", image=" + image +
                ", title='" + title + '\'' +
                ", totalAlbum=" + totalAlbum +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", serviceList=" + serviceList +
                '}';
    }
    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
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
