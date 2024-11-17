package com.example.citronix.champ.dto;

import java.util.UUID;

public record ChampResponseDTO(
        UUID id,
        String nom,
        Double superficie
) {}

