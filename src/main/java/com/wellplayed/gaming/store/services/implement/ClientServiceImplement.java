package com.wellplayed.gaming.store.services.implement;

import com.wellplayed.gaming.store.dtos.ClientDTO;
import com.wellplayed.gaming.store.models.Client;
import com.wellplayed.gaming.store.repositories.ClientRepository;
import com.wellplayed.gaming.store.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImplement implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void addClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    @Override
    public ClientDTO getClientDTO(String email) {
        return new ClientDTO(this.findByEmail(email));
    }
}