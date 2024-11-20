package com.example.citronix.ferme.validation;

import com.example.citronix.ferme.dto.request.FermeRequestDTO;

public interface FermeValidator {

    void validate(FermeRequestDTO fermeRequestDTO);
}

