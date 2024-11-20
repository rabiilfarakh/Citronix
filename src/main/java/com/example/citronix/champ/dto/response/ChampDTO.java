package com.example.citronix.champ.dto.response;

import com.example.citronix.arbre.dto.response.ArbreDTO;

import java.util.List;
import java.util.UUID;

public record ChampDTO (
        UUID id,
        String nom,
        Double superficie,
        List<ArbreDTO> arbres
){
}
