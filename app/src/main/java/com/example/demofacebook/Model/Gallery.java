package com.example.demofacebook.Model;

import java.io.Serializable;
import java.sql.Date;

public class Gallery implements Serializable {
    private int galleryId;
    private int imageGallery;
    private String GalleryName;
    private Date createDate;
    private int totalImage;



    public Gallery(int galleryId, int imageGallery, String galleryName, Date createDate, int totalImage) {
        this.galleryId = galleryId;
        this.imageGallery = imageGallery;
        GalleryName = galleryName;
        this.createDate = createDate;
        this.totalImage = totalImage;
    }

    public int getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(int galleryId) {
        this.galleryId = galleryId;
    }

    public int getImageGallery() {
        return imageGallery;
    }

    public void setImageGallery(int imageGallery) {
        this.imageGallery = imageGallery;
    }

    public String getGalleryName() {
        return GalleryName;
    }

    public void setGalleryName(String galleryName) {
        GalleryName = galleryName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getTotalImage() {
        return totalImage;
    }

    public void setTotalImage(int totalImage) {
        this.totalImage = totalImage;
    }
}
