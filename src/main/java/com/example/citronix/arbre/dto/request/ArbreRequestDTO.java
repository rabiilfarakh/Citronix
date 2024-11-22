package com.example.citronix.arbre.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public record ArbreRequestDTO(
        @NotNull(message = "La date de plantation ne peut pas être null") LocalDate datePlantation,
        @NotNull(message = "L'identifiant du champ ne peut pas être null") UUID champ_id
) {}

