package com.example.demofacebook.Model;

import java.sql.Date;

public class Feedback {
    private int avatarUser;
    private String feedbackUserName;
    private int rating;
    private String feedbackDescription;
    private int feedbackImage;
    private Date feedbackDate;


    public Feedback(int avatarUser, String feedbackUserName, int rating, String feedbackDescription, int feedbackImage, Date feedbackDate) {
        this.avatarUser = avatarUser;
        this.feedbackUserName = feedbackUserName;
        this.rating = rating;
        this.feedbackDescription = feedbackDescription;
        this.feedbackImage = feedbackImage;
        this.feedbackDate = feedbackDate;
    }

    public int getAvatarUser() {
        return avatarUser;
    }

    public void setAvatarUser(int avatarUser) {
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

    public int getFeedbackImage() {
        return feedbackImage;
    }

    public void setFeedbackImage(int feedbackImage) {
        this.feedbackImage = feedbackImage;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }
}
