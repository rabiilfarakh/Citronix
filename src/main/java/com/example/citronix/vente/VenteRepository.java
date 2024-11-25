package com.example.citronix.vente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VenteRepository extends JpaRepository<Vente, UUID> {
}
