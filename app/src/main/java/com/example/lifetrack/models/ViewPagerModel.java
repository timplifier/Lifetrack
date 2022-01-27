package com.example.lifetrack.models;

public class ViewPagerModel {
    private final String title;
    private final String description;
    private final int animation;

    public ViewPagerModel(String title, String description, int animation) {
        this.title = title;
        this.description = description;
        this.animation = animation;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getAnimation() {
        return animation;
    }
}

