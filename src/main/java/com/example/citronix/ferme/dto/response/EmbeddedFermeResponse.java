package com.example.citronix.ferme.dto.response;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public record EmbeddedFermeResponse(
        UUID id,
        String nom,
        String localisation,
        Double superficie,
        LocalDate dateCreation
) {}