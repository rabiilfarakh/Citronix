package com.example.citronix.recolte.dto.response;

import com.example.citronix.arbre.Arbre;
import com.example.citronix.enumeration.Saison;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record RecolteDTO (
        UUID id,
        LocalDate dateRecolte,
        Saison saison,
        Double quantite
){}
