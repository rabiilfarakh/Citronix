package com.example.citronix.detail_recolte.dto.request;

import com.example.citronix.recolte.dto.response.RecolteResponseDTO;

import java.util.List;
import java.util.UUID;

public record DetailRecolteRequestDTO (
        UUID arbre_id,
        UUID recolte_id,
        Double quantite
){}
