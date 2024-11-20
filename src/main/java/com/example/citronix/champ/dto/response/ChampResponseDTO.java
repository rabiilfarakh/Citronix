package com.example.citronix.champ.dto.response;

import com.example.citronix.arbre.dto.response.ArbreDTO;
import com.example.citronix.arbre.dto.response.ArbreResponseDTO;

import com.example.citronix.ferme.dto.response.EmbeddedFermeResponse;

import java.util.List;
import java.util.UUID;

public record ChampResponseDTO(
        UUID id,
        String nom,
        Double superficie,
        EmbeddedFermeResponse ferme,
        List<ArbreDTO> arbres
) {}


