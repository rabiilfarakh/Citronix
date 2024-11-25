package com.example.citronix.arbre.dto.response;

import com.example.citronix.champ.dto.response.EmbeddedChampResponse;

import java.time.LocalDate;
import java.util.UUID;

public record ArbreResponseDTO(
        UUID id,
        LocalDate datePlantation,
        EmbeddedChampResponse champ
) {}

