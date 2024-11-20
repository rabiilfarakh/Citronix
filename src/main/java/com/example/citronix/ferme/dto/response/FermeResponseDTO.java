package com.example.citronix.ferme.dto.response;

import java.util.Date;
import java.util.UUID;

import com.example.citronix.champ.dto.response.ChampDTO;
import java.util.List;

public record FermeResponseDTO(
        UUID id,
        String nom,
        String localisation,
        Double superficie,
        Date dateCreation,
        List<ChampDTO> champs
) {}


