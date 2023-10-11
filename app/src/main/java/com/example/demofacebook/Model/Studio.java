package com.example.demofacebook.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Studio implements Serializable {

    @SerializedName("studioId")
    private int studioId;
    @SerializedName("name")
    private String name;
    @SerializedName("address")
    private String address;
    @SerializedName("description")
    private String description;
    @SerializedName("status")
    private String status;
    @SerializedName("totalRating")
    private Double totalRating;
    @SerializedName("balance")
    private int balance;
    @SerializedName("createDate")
    private String createDate;
    @SerializedName("avatarStudio")
    private String avatarStudio;
    @SerializedName("coverImage")
    private String coverImage;


    public Studio(int studioId, String name, String address, String description, String status, Double totalRating, int balance, String createDate, String avatarStudio, String coverImage) {
        this.studioId = studioId;
        this.name = name;
        this.address = address;
        this.description = description;
        this.status = status;
        this.totalRating = totalRating;
        this.balance = balance;
        this.createDate = createDate;
        this.avatarStudio = avatarStudio;
        this.coverImage = coverImage;
    }

    public int getStudioId() {
        return studioId;
    }

    public void setStudioId(int studioId) {
        this.studioId = studioId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(Double totalRating) {
        this.totalRating = totalRating;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getAvatarStudio() {
        return avatarStudio;
    }

    public void setAvatarStudio(String avatarStudio) {
        this.avatarStudio = avatarStudio;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    @Override
    public String toString() {
        return "Studio{" +
                "studioId=" + studioId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", totalRating=" + totalRating +
                ", balance=" + balance +
                ", createDate='" + createDate + '\'' +
                ", avatarStudio='" + avatarStudio + '\'' +
                ", coverImage='" + coverImage + '\'' +
                '}';
    }
}
