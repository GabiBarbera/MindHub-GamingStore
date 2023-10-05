package com.wellplayed.gaming.store.dtos;

import org.hibernate.type.TextType;

import java.util.List;

public class ComponentCreateDTO {
    private long id;
    private String category;
    private String brand;
    private String name;
    private String description;
    private List<String> snapshot;
    private List<String> colors;
    private List<String> photos;
    private double price;
    private int stock;

    public ComponentCreateDTO() {
    }

    public ComponentCreateDTO(String category, String brand, String name, String description, List<String> snapshot, List<String> colors, List<String> photos, double price, int stock) {
        this.category = category;
        this.brand = brand;
        this.name = name;
        this.description = description;
        this.snapshot = snapshot;
        this.colors = colors;
        this.photos = photos;
        this.price = price;
        this.stock = stock;
    }

    public long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getSnapshot() {
        return snapshot;
    }

    public List<String> getColors() {
        return colors;
    }

    public List<String> getPhotos() {
        return photos;
    }


    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }
}