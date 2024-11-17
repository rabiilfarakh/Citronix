package com.example.citronix.ferme.dto;

import java.util.Date;
import java.util.UUID;

public record FermeResponseDTO(UUID id, String nom, String localisation, Double superficie, Date dateCreation) {
}
