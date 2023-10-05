package com.wellplayed.gaming.store.controllers;

import com.wellplayed.gaming.store.dtos.TicketDTO;
import com.wellplayed.gaming.store.dtos.TicketInfoDTO;
import com.wellplayed.gaming.store.models.*;
import com.wellplayed.gaming.store.repositories.BuyRepository;
import com.wellplayed.gaming.store.repositories.ClientRepository;
import com.wellplayed.gaming.store.repositories.ComponentRepository;
import com.wellplayed.gaming.store.repositories.TicketRepository;
import com.wellplayed.gaming.store.services.ServiceBilling;
import com.wellplayed.gaming.store.services.ServiceEmailSend;
import com.wellplayed.gaming.store.utils.RandomNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ComponentRepository componentRepository;
    @Autowired
    private ServiceBilling serviceBilling;
    @Autowired
    private BuyRepository buyRepository;
    @Autowired
    private ServiceEmailSend serviceEmailSend;
    @GetMapping("/tickets")
    public Set<TicketDTO> getTicketsDTO() {
        return ticketRepository.findAll().stream().map(order -> new TicketDTO(order)).collect(Collectors.toSet());
    }

    @GetMapping("/ticket/{id}")
    public TicketDTO getTicketForId(@PathVariable Long id) {
        return ticketRepository.findById(id).map(order -> new TicketDTO(order)).orElse(null);
    }

    @PostMapping("/ticket/build")
    public ResponseEntity<Object> buildTicket(@RequestBody Set<TicketInfoDTO> componentsCart, Authentication authentication) throws IOException {
        Client client = clientRepository.findByEmail(authentication.getName());

        double totalPurchase = 0;
        for (TicketInfoDTO component : componentsCart) {
            totalPurchase += component.getTotalAmount() * component.getQuantity();
        }

        String numberOrder = RandomNumber.getRandomNum();
        Ticket ticket = new Ticket();
        do {
            ticket.setNumberOrder(numberOrder);
        } while (ticketRepository.findByNumberOrder(numberOrder) != null);

        ticket.setDate(LocalDateTime.now());
        ticket.setClient(client);
        ticket.setAmount(totalPurchase);

        ticketRepository.save(ticket);

        for (TicketInfoDTO component : componentsCart) {
            // Crear un nuevo objeto OrdenProducto para cada producto en la orden
            Buy buy = new Buy();
            buy.setUnitPrice(component.getTotalAmount());
            buy.setQuantity(component.getQuantity());

            // Asociar el objeto OrdenProducto con la orden y el producto correspondiente
            Component componentEntity = componentRepository.findById(component.getId()).orElse(null);
            buy.setTicket(ticket);
            buy.setComponent(componentEntity);

            // Guardar el objeto OrdenProducto en la base de datos
            buyRepository.save(buy);
        }
        TicketDTO ticketDTO = new TicketDTO(ticket);
        // Enviar el correo con la factura adjunta
        try {
            // Generar el Correo
            Mail mail = new Mail();
            mail.setAddressee(client.getEmail());
            mail.setIssue("Invoice - Buy #" + ticket.getNumberOrder());
            mail.setComment("Your purchase invoice has been attached in Gaming-store.");

            // Generar el PDF
            ByteArrayOutputStream pdfData = serviceBilling.generateInvoicePDF(ticketDTO, componentsCart, client);

            // Enviar el correo electrónico con la factura adjunta
            ResponseEntity<Object> response = serviceEmailSend.sendInvoice(mail, pdfData.toByteArray());
            if (response.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<>("Order generated and email sent successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Order generated, but there was a problem sending the email", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            // Si ocurre alguna excepción al generar la factura o enviar el correo, manejarla aquí
            e.printStackTrace();
            return new ResponseEntity<>("Error generating the invoice or sending the email", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}