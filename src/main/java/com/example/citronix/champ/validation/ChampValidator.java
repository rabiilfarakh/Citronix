package com.example.citronix.champ.validation;

import com.example.citronix.champ.dto.request.ChampRequestDTO;
import com.example.citronix.ferme.Ferme;

public interface ChampValidator {

    void validate(ChampRequestDTO champRequestDTO, Ferme ferme);
}
