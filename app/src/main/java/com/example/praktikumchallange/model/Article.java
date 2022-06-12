package com.example.praktikumchallange.model;

import androidx.annotation.DrawableRes;

public class Article {
    public String key, title, description, publisher, image;

    public Article() {}

    public Article(String title, String description, String publisher, String image) {
        this.title = title;
        this.description = description;
        this.publisher = publisher;
        this.image = image;
    }
}