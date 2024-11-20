package com.example.citronix.champ;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ChampRepository extends JpaRepository<Champ, UUID> {
    long countByFermeId(UUID fermeId);
}
