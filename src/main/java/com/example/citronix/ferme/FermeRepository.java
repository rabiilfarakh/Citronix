package com.example.citronix.ferme;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface FermeRepository extends JpaRepository<Ferme, UUID> {
}
