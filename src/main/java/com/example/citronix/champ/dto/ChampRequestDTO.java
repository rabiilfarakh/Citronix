package com.example.citronix.champ.dto;


import jakarta.validation.constraints.NotNull;

public record ChampRequestDTO(
        @NotNull(message = "Le nom du champ ne peut pas être null") String nom,
        @NotNull(message = "La superficie du champ ne peut pas être null") Double superficie
) {}

