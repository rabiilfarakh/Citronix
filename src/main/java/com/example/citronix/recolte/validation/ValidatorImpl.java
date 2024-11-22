package com.example.citronix.recolte.validation;

import com.example.citronix.arbre.service.ArbreService;
import com.example.citronix.champ.Champ;
import com.example.citronix.detail_recolte.service.DetailRecolteService;
import com.example.citronix.enumeration.Saison;
import com.example.citronix.recolte.Recolte;
import com.example.citronix.recolte.RecolteRepository;
import com.example.citronix.detail_recolte.dto.request.DetailRecolteRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Component
public class ValidatorImpl implements Validator {

    private final ArbreService arbreService;
    private final RecolteRepository recolteRepository;
    private final DetailRecolteService detailRecolteService;

    @Override
    public void validateChamp(Champ champ) {
        if (champ.getArbres() == null || champ.getArbres().isEmpty()) {
            throw new RuntimeException("Le champ avec ID: " + champ.getId() + " ne contient aucun arbre.");
        }
    }

    @Override
    public Double calculateQuantite(Champ champ) {
        return champ.getArbres()
                .stream()
                .mapToDouble(arbre -> Optional.ofNullable(arbreService.productivite(arbre.getId()))
                        .orElseThrow(() -> new RuntimeException("Impossible de calculer la productivité pour l'arbre avec ID: " + arbre.getId())))
                .sum();
    }

    @Override
    public Saison determineSaison() {
        int mois = LocalDate.now().getMonthValue();
        if (mois >= 3 && mois <= 5) {
            return Saison.PRINTEMPS;
        } else if (mois >= 6 && mois <= 8) {
            return Saison.ETE;
        } else if (mois >= 9 && mois <= 11) {
            return Saison.AUTOMNE;
        } else {
            return Saison.HIVER;
        }
    }

    @Override
    public void saveDetailRecolte(Champ champ, Recolte savedRecolte) {
        champ.getArbres().forEach(arbre -> {
            DetailRecolteRequestDTO detailRecolteRequestDTO = new DetailRecolteRequestDTO(
                    arbre.getId(),
                    savedRecolte.getId(),
                    arbreService.productivite(arbre.getId())
            );
            detailRecolteService.save(detailRecolteRequestDTO);
        });
    }

    @Override
    public void validateRecolteInterval(UUID champId) {
        List<Recolte> recoltes = recolteRepository.findByChampId(champId);
        recoltes.sort((r1, r2) -> r2.getDateRecolte().compareTo(r1.getDateRecolte()));

        if (!recoltes.isEmpty()) {
            LocalDate derniereDateRecolte = recoltes.get(0).getDateRecolte();
            LocalDate dateLimite = derniereDateRecolte.plusMonths(4);

            if (LocalDate.now().isBefore(dateLimite)) {
                throw new RuntimeException("Une récolte ne peut pas être effectuée moins de 4 mois après la dernière récolte.");
            }
        }
    }
}
