package ru.startandroid.develop.p0541customadapter;

/**
 * Created by Freeman on 30.01.2017.
 */

public class Product {
    private String name;
    private int price;
    private int image;
    private boolean box;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return String.valueOf(price);
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean isBox() {
        return box;
    }

    public void setBox(boolean box) {
        this.box = box;
    }

    public Product(String name, int price, int image, boolean box) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.box = box;
    }
}
