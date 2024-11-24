package com.example.citronix.recolte.dto.response;

import com.example.citronix.arbre.Arbre;
import com.example.citronix.enumeration.Saison;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public record RecolteResponseDTO (
        UUID id,
        LocalDate dateRecolte,
        Saison saison,
        List<Arbre> arbres
){}
