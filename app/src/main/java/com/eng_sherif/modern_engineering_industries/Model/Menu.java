package com.eng_sherif.modern_engineering_industries.Model;

public class Menu {

    private int image;
    private String title;

    public Menu() {

    }

    public Menu(int image, String title) {
        this.image = image;
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
