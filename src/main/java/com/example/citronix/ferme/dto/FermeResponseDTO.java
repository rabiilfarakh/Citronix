package com.example.citronix.ferme.dto;

import java.util.Date;
import java.util.UUID;
import com.example.citronix.champ.dto.ChampResponseDTO;
import java.util.List;

public record FermeResponseDTO(
        UUID id,
        String nom,
        String localisation,
        Double superficie,
        Date dateCreation,
        List<ChampResponseDTO> champs
) {}

