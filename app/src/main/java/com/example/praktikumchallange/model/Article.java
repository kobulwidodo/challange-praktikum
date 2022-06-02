package com.example.praktikumchallange.model;

import androidx.annotation.DrawableRes;

public class Article {
    public @DrawableRes
    int imageResourceId;
    public @DrawableRes
    int imageDetailId;
    public String title;
    public String description;
    public String publisher;

    public Article(@DrawableRes int imageResourceId, @DrawableRes int imageDetailId, String title, String description, String publisher) {
        this.imageResourceId = imageResourceId;
        this.imageDetailId = imageDetailId;
        this.title = title;
        this.description = description;
        this.publisher = publisher;
    }
}