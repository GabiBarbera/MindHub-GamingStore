package com.wellplayed.gaming.store.dtos;

import com.wellplayed.gaming.store.models.Buy;

import java.util.List;

public class BuyDTO {
    private long id;
    private int quantity;
    private double unitPrice;


    public BuyDTO() {
    }

    public BuyDTO(Buy buy) {
        this.id = buy.getId();
        this.quantity = buy.getQuantity();
        this.unitPrice = buy.getUnitPrice();
    }

    public long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

}