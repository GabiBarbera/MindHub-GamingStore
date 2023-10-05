package com.wellplayed.gaming.store.repositories;

import com.wellplayed.gaming.store.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Ticket findByNumberOrder(String numberOrder);
}
