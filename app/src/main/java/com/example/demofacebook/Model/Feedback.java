package com.example.demofacebook.Model;

import com.google.gson.annotations.SerializedName;

public class Feedback {

    @SerializedName("orderDetailId")
    private int feedbackId;
    private String avatarUser;
    private String feedbackUserName;
    @SerializedName("rating")
    private int rating;
    @SerializedName("content")
    private String feedbackDescription;
    @SerializedName("postDate")
    private String feedbackDate;


    public Feedback(int feedbackId, String avatarUser, String feedbackUserName, int rating, String feedbackDescription, String feedbackDate) {
        this.feedbackId = feedbackId;
        this.avatarUser = avatarUser;
        this.feedbackUserName = feedbackUserName;
        this.rating = rating;
        this.feedbackDescription = feedbackDescription;
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

    public String getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(String feedbackDate) {
        this.feedbackDate = feedbackDate;
    }
}
