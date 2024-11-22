package com.example.citronix.detail_recolte.dto.response;

import java.util.UUID;

public record DetailRecolteResponseDTO (
    UUID id,
    UUID arbre_id,
    UUID recolte_id,
    Double quantite
){}
