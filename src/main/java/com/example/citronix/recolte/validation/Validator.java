package com.example.citronix.recolte.validation;

import com.example.citronix.champ.Champ;
import com.example.citronix.recolte.Recolte;
import com.example.citronix.enumeration.Saison;

import java.util.UUID;

public interface Validator {

    void validateChamp(Champ champ);

    Double calculateQuantite(Champ champ);

    Saison determineSaison();

    void saveDetailRecolte(Champ champ, Recolte savedRecolte);

    void validateRecolteInterval(UUID champId);
}
