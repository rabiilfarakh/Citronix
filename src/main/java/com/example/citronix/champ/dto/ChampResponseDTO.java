package com.example.citronix.champ.dto;

import com.example.citronix.arbre.dto.ArbreResponseDTO;
import java.util.List;
import java.util.UUID;

public record ChampResponseDTO(
        UUID id,
        String nom,
        Double superficie,
        List<ArbreResponseDTO> arbres
) {}

