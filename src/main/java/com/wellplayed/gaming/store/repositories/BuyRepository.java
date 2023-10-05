package com.wellplayed.gaming.store.repositories;

import com.wellplayed.gaming.store.models.Buy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BuyRepository extends JpaRepository<Buy, Long> {
}
