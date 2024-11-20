package com.example.citronix.arbre.dto.response;

import com.example.citronix.champ.dto.response.EmbeddedChampResponse;

import java.util.Date;
import java.util.UUID;

public record ArbreResponseDTO(
        UUID id,
        Date datePlantation,
        EmbeddedChampResponse champ
) {}

