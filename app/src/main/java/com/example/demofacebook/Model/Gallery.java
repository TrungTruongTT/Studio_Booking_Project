package com.example.demofacebook.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Gallery implements Serializable {
    @SerializedName("albumId")
    private int galleryId;
    private String imageGallery;
    @SerializedName("title")
    private String GalleryName;
    @SerializedName("createDate")
    private Date createDate;
    private int totalImage;
    @SerializedName("album_mediaFile")
    private List<GalleryItem> galleryItems;

    public Gallery(int galleryId, String galleryName, Date createDate, List<GalleryItem> galleryItems) {
        this.galleryId = galleryId;
        this.imageGallery = galleryItems.get(0).getImageItemGallery();
        GalleryName = galleryName;
        this.createDate = createDate;
        this.galleryItems = galleryItems;
        this.totalImage = galleryItems.size();
    }

    public int getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(int galleryId) {
        this.galleryId = galleryId;
    }

    public String getImageGallery() {
        return imageGallery;
    }

    public void setImageGallery(String imageGallery) {
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

    public List<GalleryItem> getGalleryItems() {
        return galleryItems;
    }

    public void setGalleryItems(List<GalleryItem> galleryItems) {
        this.galleryItems = galleryItems;
    }
}
