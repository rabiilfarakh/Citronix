package com.example.citronix.ferme.dto.response;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import com.example.citronix.champ.dto.response.ChampDTO;
import java.util.List;

public record FermeResponseDTO(
        UUID id,
        String nom,
        String localisation,
        Double superficie,
        LocalDate dateCreation,
        List<ChampDTO> champs
) {}


