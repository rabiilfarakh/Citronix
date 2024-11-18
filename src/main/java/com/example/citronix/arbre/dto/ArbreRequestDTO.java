package com.example.citronix.arbre.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ArbreRequestDTO(
        @NotNull(message = "La date de plantation d'arbre ne peut pas être null")
        Date datePlantation
) {
}
