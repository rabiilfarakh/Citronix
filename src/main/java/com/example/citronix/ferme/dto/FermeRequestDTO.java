package com.example.citronix.ferme.dto;

import java.util.Date;

public record FermeRequestDTO(String nom, String localisation, Double superficie, Date dateCreation) {
}
