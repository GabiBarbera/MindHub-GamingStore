package com.wellplayed.gaming.store.dtos;

import com.wellplayed.gaming.store.models.Component;

import java.util.List;

public class ComponentDTO {
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

    public ComponentDTO() {
    }

    public ComponentDTO(Component component) {
        this.id = component.getId();
        this.category = component.getCategory();
        this.brand = component.getBrand();
        this.name = component.getName();
        this.description = component.getDescription();
        this.snapshot = component.getSnapshot();
        this.colors = component.getColors();
        this.photos = component.getPhotos();
        this.price = component.getPrice();
        this.stock = component.getStock();
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
