package com.wellplayed.gaming.store.dtos;

import com.wellplayed.gaming.store.models.Client;

public class ClientDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public ClientDTO() {
    }
    public ClientDTO(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.phoneNumber = client.getPhoneNumber();
    }
    public long getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}