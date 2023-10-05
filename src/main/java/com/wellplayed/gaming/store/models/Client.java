package com.wellplayed.gaming.store.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private int dni;
    private int postalCode;
    private String address;
    private String phoneNumber;
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Ticket> tickets = new HashSet<>();

    public Client() {
    }

    public Client(String email, String password, int dni, int postalCode, String address, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.dni = dni;
        this.postalCode = postalCode;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Client(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(short dni) {
        this.dni = dni;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(short postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket) {
        ticket.setClient(this);
        tickets.add(ticket);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
}