package com.wellplayed.gaming.store.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String numberOrder;
    private double amount;
    private LocalDateTime date;
    private BuyType type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "ticket", fetch = FetchType.EAGER)
    private Set<Buy> buys = new HashSet<>();

    public Ticket() {
    }

    public Ticket(String numberOrder,double amount, LocalDateTime date, BuyType type) {
        this.numberOrder = numberOrder;
        this.amount = amount;
        this.date = date;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public String getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(String numberOrder) {
        this.numberOrder = numberOrder;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BuyType getType() {
        return type;
    }

    public void setType(BuyType type) {
        this.type = type;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Buy> getBuys() {
        return buys;
    }

    public void setBuys(Set<Buy> buys) {
        this.buys = buys;
    }
    public void addBuy(Buy buy) {
        buy.setTicket(this);
        buys.add(buy);
    }
}