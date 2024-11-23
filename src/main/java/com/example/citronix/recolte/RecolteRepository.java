package com.example.citronix.recolte;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.UUID;

@Repository
public interface RecolteRepository extends JpaRepository<Recolte, UUID> {
    Optional<Recolte> findTopByChampIdOrderByDateRecolteDesc(UUID champId);

}
