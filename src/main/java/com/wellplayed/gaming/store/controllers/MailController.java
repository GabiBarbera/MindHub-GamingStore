package com.wellplayed.gaming.store.controllers;

import com.wellplayed.gaming.store.dtos.MailDTO;
import com.wellplayed.gaming.store.models.Mail;
import com.wellplayed.gaming.store.repositories.ClientRepository;
import com.wellplayed.gaming.store.services.ServiceBilling;
import com.wellplayed.gaming.store.services.ServiceEmailSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class MailController {
    @Autowired
    private ServiceEmailSend serviceEmailSend;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ServiceBilling serviceBilling;

    @PostMapping("/sendemail")
    public ResponseEntity<String> sendEmail(@RequestBody MailDTO mailDTO) {
        try{        // Verificar que se hayan proporcionado los datos necesarios
            if ( mailDTO.getComment() == null || mailDTO.getComment().isBlank()) {
                return new ResponseEntity<>("The comment is missing from the form", HttpStatus.BAD_REQUEST);
            }
            if (mailDTO.getIssue().isBlank() || mailDTO.getIssue() == null){
                return new ResponseEntity<>("Enter the subject", HttpStatus.BAD_REQUEST);
            }

            // Crear un mensaje de correo electrónico
            Mail mail = new Mail();
            mail.setAddressee("julianbrunelli21@gmail.com"); // Reemplaza con la dirección de correo destino
            mail.setIssue(mailDTO.getIssue());
            mail.setComment(mailDTO.getComment());

            // Enviar el correo electrónico utilizando el EmailSender
            serviceEmailSend.sendEmail(mail);

            return new ResponseEntity<>("Email sent successfully", HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error sending email", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}