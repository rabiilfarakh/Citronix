package com.example.citronix.recolte.dto.request;

import com.example.citronix.enumeration.Saison;

import java.time.LocalDate;
import java.util.UUID;

public record RecolteRequestDTO (
        LocalDate dateRecolte,
        UUID champ_id,
        Double quantite
){}
