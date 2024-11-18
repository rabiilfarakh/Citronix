package com.example.citronix.ferme.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record FermeRequestDTO(
        @NotNull(message = "Le nom ne peut pas être null") String nom,
        @NotNull(message = "La localisation ne peut pas être null") String localisation,
        @NotNull(message = "La superficie ne peut pas être null") Double superficie,
        @NotNull(message = "La date de création ne peut pas être null") Date dateCreation,
        List<UUID> champIds
) {}

