package com.example.citronix.arbre.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.UUID;

public record ArbreRequestDTO(
        @NotNull(message = "La date de plantation ne peut pas être null") Date datePlantation,
        @NotNull(message = "L'identifiant du champ ne peut pas être null") UUID champ_id
) {}

