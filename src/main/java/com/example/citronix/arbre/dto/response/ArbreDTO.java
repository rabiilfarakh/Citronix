package com.example.citronix.arbre.dto.response;

import java.util.Date;
import java.util.UUID;

public record ArbreDTO(
        UUID id,
        Date datePlantation
) {
}
