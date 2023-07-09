package com.example.demofacebook.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Studio implements Serializable {
    @SerializedName("studio_id")
    private int studioId;
    @SerializedName("avatar_studio")
    private int image;
    @SerializedName("name")
    private String title;
    @SerializedName("address")

    private String address_Studio;
    private int totalAlbum;
    @SerializedName("total_rating")
    private int rating;
    @SerializedName("description")
    private String description;
    private List<Service> serviceList;

    public Studio(int studioId, int image, String title, String address_Studio, int rating, String description) {
        this.studioId = studioId;
        this.image = image;
        this.title = title;
        this.address_Studio = address_Studio;
        this.rating = rating;
        this.description = description;
    }

    public Studio(int studioId, int image, String title, int totalAlbum, int rating, String description, List<Service> serviceList) {
        this.studioId = studioId;
        this.image = image;
        this.title = title;
        this.totalAlbum = totalAlbum;
        this.rating = rating;
        this.description = description;
        this.serviceList = serviceList;
    }


    public Studio(int studioId, int image, String title, int totalAlbum, int rating, String description) {
        this.studioId = studioId;
        this.image = image;
        this.title = title;
        this.totalAlbum = totalAlbum;
        this.rating = rating;
        this.description = description;
    }

    public Studio(int studioId, int image, String title, int rating, String description) {
        this.studioId = studioId;
        this.image = image;
        this.title = title;
        this.rating = rating;
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

    public String getAddress_Studio() {
        return address_Studio;
    }

    public void setAddress_Studio(String address_Studio) {
        this.address_Studio = address_Studio;
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
