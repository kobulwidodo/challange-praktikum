package com.example.praktikumchallange.model;

import androidx.annotation.DrawableRes;

public class Shoes {
    public @DrawableRes
    int imageResourceId;
    public @DrawableRes
    int imageDetailId;
    public String type;
    public String title;
    public String price;
    public String description;

    public Shoes(@DrawableRes int imageResourceId, @DrawableRes int imageDetailId, String type, String title, String price, String description) {
        this.imageResourceId = imageResourceId;
        this.imageDetailId = imageDetailId;
        this.type = type;
        this.title = title;
        this.price = price;
        this.description = description;
    }
}