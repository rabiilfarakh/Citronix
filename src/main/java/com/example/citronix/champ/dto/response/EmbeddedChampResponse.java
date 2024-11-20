package com.example.citronix.champ.dto.response;

import com.example.citronix.ferme.dto.response.EmbeddedFermeResponse;

import java.util.UUID;

public record EmbeddedChampResponse(
    UUID id,
    String nom,
    Double superficie,
    EmbeddedFermeResponse ferme
    ){}
