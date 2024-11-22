package com.example.citronix.detail_recolte;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DetailRecolteRepository extends JpaRepository<DetailRecolte,UUID> {
}
