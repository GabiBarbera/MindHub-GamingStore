package com.wellplayed.gaming.store.services;

import com.wellplayed.gaming.store.dtos.ClientDTO;
import com.wellplayed.gaming.store.models.Client;

public interface ClientService {
    void addClient(Client client);
    Client findByEmail(String email);
    ClientDTO getClientDTO(String email);
}