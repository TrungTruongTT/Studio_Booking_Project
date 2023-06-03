package com.example.demofacebook.Model;

public class Category {
    private int imageCategory;
    private String categoryName;

    public Category(int imageCategory, String categoryName) {
        this.imageCategory = imageCategory;
        this.categoryName = categoryName;
    }

    public int getImageCategory() {
        return imageCategory;
    }

    public void setImageCategory(int imageCategory) {
        this.imageCategory = imageCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
