package com.wellplayed.gaming.store.dtos;

import com.wellplayed.gaming.store.models.Buy;
import com.wellplayed.gaming.store.models.BuyType;
import com.wellplayed.gaming.store.models.Ticket;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
public class TicketDTO {
    private long id;
    private String numberOrder;
    private double amount;
    private LocalDateTime date;
    private BuyType type;
    private Set<BuyDTO> buys;

    public TicketDTO() {
    }

    public TicketDTO(Ticket ticket) {
        this.id = ticket.getId();
        this.numberOrder = ticket.getNumberOrder();
        this.amount = ticket.getAmount();
        this.date = ticket.getDate();
        this.type = ticket.getType();
        this.buys = ticket.getBuys().stream().map(buy -> new BuyDTO(buy)).collect(Collectors.toSet());
    }

    public long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public BuyType getType() {
        return type;
    }

    public String getNumberOrder() {
        return numberOrder;
    }

    public Set<BuyDTO> getBuys() {
        return buys;
    }
}
