package com.example.demofacebook.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Studio implements Serializable {
    @SerializedName("studioId")
    private int studioId;
    @SerializedName("avatarStudio")
    private String image;
    @SerializedName("name")
    private String title;
    @SerializedName("address")

    private String address_Studio;
    private int totalAlbum;
    @SerializedName("totalRating")
    private double rating;
    @SerializedName("description")
    private String description;

    @SerializedName("coverImage")
    private String coverImage;
    private List<Service> serviceList;

    public Studio(int studioId, String image, String title, String address_Studio, double rating, String description, String coverImage) {
        this.studioId = studioId;
        this.image = image;
        this.title = title;
        this.address_Studio = address_Studio;
        this.rating = rating;
        this.description = description;
        this.coverImage = coverImage;
    }

    public Studio(int studioId, String image, String title, int totalAlbum, double rating, String description, String coverImage, List<Service> serviceList) {
        this.studioId = studioId;
        this.image = image;
        this.title = title;
        this.totalAlbum = totalAlbum;
        this.rating = rating;
        this.description = description;
        this.coverImage = coverImage;
        this.serviceList = serviceList;
    }


    public Studio(int studioId, String image, String title, int totalAlbum, double rating, String description, String coverImage) {
        this.studioId = studioId;
        this.image = image;
        this.title = title;
        this.totalAlbum = totalAlbum;
        this.rating = rating;
        this.description = description;
        this.coverImage = coverImage;
    }

    public Studio(int studioId, String image, String title, double rating, String description, String coverImage) {
        this.studioId = studioId;
        this.image = image;
        this.title = title;
        this.rating = rating;
        this.description = description;
        this.coverImage = coverImage;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }


    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
}
