package com.example.citronix.ferme.dto.response;

import java.util.Date;
import java.util.UUID;

public record EmbeddedFermeResponse(
        UUID id,
        String nom,
        String localisation,
        Double superficie,
        Date dateCreation
) {}