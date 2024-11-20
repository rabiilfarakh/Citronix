package com.example.citronix.champ.dto.request;


import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record ChampRequestDTO(
        @NotNull(message = "Le nom ne peut pas être null") String nom,
        @NotNull(message = "La superficie ne peut pas être null") Double superficie,
        @NotNull(message = "L'identifiant de la ferme ne peut pas être null") UUID ferme_id
) {}


