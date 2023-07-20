package com.example.demofacebook.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Feedback {

    @SerializedName("orderDetailId")
    private int feedbackId;
    private String avatarUser;
    private String feedbackUserName;
    @SerializedName("rating")
    private int rating;
    @SerializedName("content")
    private String feedbackDescription;
    //    @SerializedName("servicePack_mediaService")
    //    private int feedbackImage;
    @SerializedName("servicePack_mediaService")
    private List<Media_ServicePack> feedbackImage;
    @SerializedName("postDate")
    private String feedbackDate;


    public Feedback(int feedbackId, String avatarUser, String feedbackUserName, int rating, String feedbackDescription, List<Media_ServicePack> feedbackImage, String feedbackDate) {
        this.feedbackId = feedbackId;
        this.avatarUser = avatarUser;
        this.feedbackUserName = feedbackUserName;
        this.rating = rating;
        this.feedbackDescription = feedbackDescription;
        this.feedbackImage = feedbackImage;
        this.feedbackDate = feedbackDate;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public void setFeedbackUserName(String feedbackUserName) {
        this.feedbackUserName = feedbackUserName;
    }
    public String getAvatarUser() {
        return avatarUser;
    }

    public void setAvatarUser(String avatarUser) {
        this.avatarUser = avatarUser;
    }

    public String getFeedbackUserName() {
        return feedbackUserName;
    }

    public void setGalleryName(String galleryName) {
        feedbackUserName = galleryName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFeedbackDescription() {
        return feedbackDescription;
    }

    public void setFeedbackDescription(String feedbackDescription) {
        this.feedbackDescription = feedbackDescription;
    }

    public List<Media_ServicePack> getFeedbackImage() {
        return feedbackImage;
    }

    public void setFeedbackImage(List<Media_ServicePack> feedbackImage) {
        this.feedbackImage = feedbackImage;
    }

    public String getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(String feedbackDate) {
        this.feedbackDate = feedbackDate;
    }
}
