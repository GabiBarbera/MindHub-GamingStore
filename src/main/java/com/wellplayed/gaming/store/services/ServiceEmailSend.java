package com.wellplayed.gaming.store.services;

import com.wellplayed.gaming.store.models.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Service
public class ServiceEmailSend {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(Mail messageMail) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        String username = "gamingstore521@gmail.com";
        String password = "gaming-store";
        String sender = messageMail.getSender();
        String issue = messageMail.getIssue();
        String comment = messageMail.getComment();
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Creación del mensaje de correo
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(messageMail.getSender()));
            message.setSubject(messageMail.getIssue());

            // Construcción del contenido del mensaje con el comentario y la imagen
            Multipart multiPart = new MimeMultipart();
            BodyPart commentPart = new MimeBodyPart();
            commentPart.setText("Issue: Response to your contact message\n" +
                    "\n" +
                    "Dear" + " " + sender +
                    "\n" +
                    "We want to thank you for taking the time to contact us through our form. We value your interest in Gaming Store and are excited to serve you.\n" +
                    "\n" +
                    "We have received your message and we are pleased to know that you are interested in our products/services. Your opinion and questions are essential to us, and we will do our best to provide you with a detailed and timely response.\n" +
                    "\n" +
                    "Our support team will review your message carefully and respond as soon as possible. If your query requires immediate attention, we recommend that you contact us via email 'gamingstore521@gmail.com'\n" +
                    "\n" +
                    "Sincerely\n" +
                    "\n" +
                    "Gaming Store\n" +
                    "gamingstore521@gmail.com\n");

            multiPart.addBodyPart(commentPart);
            message.setContent(multiPart);

            // Enviar el correo al remitente
            Transport.send(message);

            // Enviar un segundo correo al destinatario (username) con el contenido del comentario
            Message messageToUsername = new MimeMessage(session);
            messageToUsername.setFrom(new InternetAddress(username));
            messageToUsername.addRecipient(Message.RecipientType.TO, new InternetAddress(username));
            messageToUsername.setSubject("Message sent by: " + sender + " + " + "the issue is:" + " " + issue + ".");
            messageToUsername.setText("The user (" + sender + ") submitted this comment:" + " " + comment + ".");
            Transport.send(messageToUsername);
            Transport transport = session.getTransport("smtp");
            transport.connect(username, password);
            transport.sendMessage(messageToUsername, messageToUsername.getRecipients(Message.RecipientType.TO));
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Error sending email.", e);
        }
    }

    public ResponseEntity<Object> sendInvoice(Mail messageMail, byte[] pdfData) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        String username = "gamingstore521@gmail.com";
        String password = "gaming-store";
        String addressee = messageMail.getAddressee();
        String issue = messageMail.getIssue();
        String comment = messageMail.getComment();
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(new InternetAddress(username));
            helper.setTo(new InternetAddress(addressee));
            helper.setSubject(issue);

            helper.setText(comment);

            MimeMessage messageGamingStore = javaMailSender.createMimeMessage();
            MimeMessageHelper helpGamingStore = new MimeMessageHelper(messageGamingStore, true);
            helpGamingStore.setFrom(new InternetAddress(username));
            helpGamingStore.setSubject(issue);

            String messageForGamingStore = "This is the receipt for your invoice.";

            helpGamingStore.setText(messageForGamingStore);

            // Agregar el archivo PDF adjunto
            helper.addAttachment("invoice.pdf", new ByteArrayResource(pdfData));
            helpGamingStore.addAttachment("invoice.pdf", new ByteArrayResource(pdfData));
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Error sending email.", e);
        }

        return new ResponseEntity<>("Email send", HttpStatus.OK);
    }
}