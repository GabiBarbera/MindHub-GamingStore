package com.wellplayed.gaming.store.controllers;

import com.wellplayed.gaming.store.dtos.ClientDTO;
import com.wellplayed.gaming.store.models.Client;
import com.wellplayed.gaming.store.repositories.ClientRepository;
import com.wellplayed.gaming.store.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api")
public class ClientController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/clients")
    public List<ClientDTO> getClients() {
        return clientRepository.findAll().stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());
    }

    @GetMapping("/clients/current")
    public ClientDTO getClient(Authentication authentication) {
        return clientService.getClientDTO(authentication.getName());
    }

    @GetMapping("/client/authenticate")
    public ResponseEntity<Object> authenticate(Authentication authentication) {
        if(authentication!=null){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @PostMapping("/clients/register")
    public ResponseEntity<Object> register(
            @RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String password) {
        if (firstName.isBlank()) {
            return new ResponseEntity<>("The name is missing, please enter it.", HttpStatus.FORBIDDEN);
        }
        if (lastName.isBlank()) {
            return new ResponseEntity<>("The last name is missing, please enter it.", HttpStatus.FORBIDDEN);
        }
        if (email.isBlank()) {
            return new ResponseEntity<>("The email is missing, please enter it.", HttpStatus.FORBIDDEN);
        }
        if (!email.matches("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$")) {
            return new ResponseEntity<>("Incorrect email format.", HttpStatus.FORBIDDEN);
        }
        if (password.isBlank()) {
            return new ResponseEntity<>("The password is missing, please enter it.", HttpStatus.FORBIDDEN);
        }
        if (!password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
            return new ResponseEntity<>("The password must contain at least 8 characters, a capital letter, a number and a special character!", HttpStatus.FORBIDDEN);
        }
        if (clientRepository.findByEmail(email) != null) {
            return new ResponseEntity<>("Email already in use", HttpStatus.FORBIDDEN);
        }
        Client newClient = new Client(firstName, lastName, email, passwordEncoder.encode(password));
        clientService.addClient(newClient);
        return new ResponseEntity<>("User created successfully.", HttpStatus.CREATED);
    }
}