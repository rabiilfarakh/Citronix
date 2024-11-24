package com.example.citronix.vente.dto.request;

import java.time.LocalDate;
import java.util.UUID;

public record VenteRequestDTO(
        UUID recolte_id,
        String cin,
        LocalDate date,
        Double quantite,
        Double prix
) {
}
