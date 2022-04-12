package com.example.hw4.model;

public class BoardModel {
    private int image;
    private String description;
    private  String nextBtn;

    public String getNextBtn() {
        return nextBtn;
    }

    public int getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }



    public BoardModel(int image, String description, String nextBtn) {
        this.image = image;
        this.description = description;
        this.nextBtn = nextBtn;
    }
}
