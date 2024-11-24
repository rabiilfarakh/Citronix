package com.example.citronix.vente.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record VenteResponseDTO (
        UUID id,
        String cin,
        LocalDate date,
        Double quantite,
        Double prix
){

}
