package com.example.citronix.champ.validation;

import com.example.citronix.champ.ChampRepository;
import com.example.citronix.champ.dto.request.ChampRequestDTO;
import com.example.citronix.ferme.Ferme;
import com.example.citronix.ferme.dto.request.FermeRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ChampValidatorImpl implements ChampValidator {
    private final ChampRepository champRepository;


    @Override
    public void validate(ChampRequestDTO champRequestDTO, Ferme ferme) {
        if (champRequestDTO.superficie() < 0.1) {
            throw new IllegalArgumentException("La superficie d'un champ doit être d'au moins 0.1 hectare.");
        }

        if (champRequestDTO.superficie() > (ferme.getSuperficie() * 0.5)) {
            throw new IllegalArgumentException("La superficie d'un champ ne peut pas dépasser 50% de la superficie totale de la ferme.");
        }

        long nombreChamps = champRepository.countByFermeId(champRequestDTO.ferme_id());
        if (nombreChamps >= 10) {
            throw new IllegalArgumentException("Une ferme ne peut contenir plus de 10 champs.");
        }
    }

}

