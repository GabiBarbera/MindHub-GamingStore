package com.wellplayed.gaming.store.dtos;

import com.wellplayed.gaming.store.models.Buy;

public class TicketInfoDTO {
    private long id;
    private double totalAmount;
    private int quantity;
    public TicketInfoDTO() {
    }

    public TicketInfoDTO(Buy buy) {
        this.id = buy.getId();
        this.totalAmount = buy.getUnitPrice();
        this.quantity = buy.getQuantity();
    }

    public long getId() {
        return id;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public int getQuantity() {
        return quantity;
    }
}