package com.example.citronix.ferme.validation;

import com.example.citronix.ferme.FermeRepository;
import com.example.citronix.ferme.dto.request.FermeRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class FermeValidatorImpl implements FermeValidator {

    private final FermeRepository fermeRepository;

    @Override
    public void validate(FermeRequestDTO fermeRequestDTO) {
        if (fermeRequestDTO.superficie() < 1000) {
            throw new IllegalArgumentException("La superficie de la ferme doit être d'au moins 0.1 hectare.");
        }

        long totalChamps = fermeRepository.count();
        if (totalChamps >= 10) {
            throw new IllegalArgumentException("Une ferme ne peut pas contenir plus de 10 champs.");
        }
    }
}


