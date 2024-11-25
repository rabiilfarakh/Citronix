package com.example.citronix.recolte.service;

import com.example.citronix.arbre.service.ArbreService;
import com.example.citronix.champ.Champ;
import com.example.citronix.champ.service.ChampService;
import com.example.citronix.detail_recolte.dto.request.DetailRecolteRequestDTO;
import com.example.citronix.detail_recolte.service.DetailRecolteService;
import com.example.citronix.enumeration.Saison;
import com.example.citronix.recolte.Recolte;
import com.example.citronix.recolte.RecolteMapper;
import com.example.citronix.recolte.RecolteRepository;
import com.example.citronix.recolte.dto.request.RecolteRequestDTO;
import com.example.citronix.recolte.dto.response.RecolteDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RecolteServiceImpl implements RecolteService {

    private final RecolteRepository recolteRepository;
    private final ArbreService arbreService;
    private final ChampService champService;
    private final RecolteMapper recolteMapper;
    private final DetailRecolteService detailRecolteService;

    private static final int MOIS_LIMIT = 3;

    @Override
    public RecolteDTO save(RecolteRequestDTO recolteRequestDTO) {
        Champ champ = champService.findChampById(recolteRequestDTO.champ_id());
        if (!canHarvest(recolteRequestDTO.champ_id(), recolteRequestDTO.dateRecolte())) {
            throw new IllegalArgumentException("Une récolte ne peut être effectuée que 4 mois après la précédente.");
        }

        Double quantite = calculateTotalQuantite(champ);

        Recolte recolte = recolteMapper.toEntity(recolteRequestDTO);
        recolte.setChamp(champ);
        recolte.setQuantite(quantite);

        recolte.setSaison(determineCurrentSeason());

        Recolte savedRecolte = recolteRepository.save(recolte);

        saveArbreDetails(champ, savedRecolte);

        return recolteMapper.toDTO(savedRecolte);
    }

    @Override
    public RecolteDTO update(UUID id, RecolteRequestDTO recolteRequestDTO) {
        Recolte existingRecolte = recolteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Récolte introuvable avec l'ID : " + id));

        existingRecolte.setDateRecolte(recolteRequestDTO.dateRecolte());
        Champ champ = champService.findChampById(recolteRequestDTO.champ_id());

        if (champ == null) {
            throw new IllegalArgumentException("Champ non trouvé pour l'ID : " + recolteRequestDTO.champ_id());
        }

        existingRecolte.setQuantite(calculateTotalQuantite(champ));

        Recolte updatedRecolte = recolteRepository.save(existingRecolte);
        return recolteMapper.toDTO(updatedRecolte);
    }

    @Override
    public Optional<RecolteDTO> findById(UUID id) {
        return recolteRepository.findById(id)
                .map(recolteMapper::toDTO);
    }

    @Override
    public List<RecolteDTO> findAll() {
        return recolteRepository.findAll()
                .stream()
                .map(recolteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        Recolte recolte = recolteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Récolte introuvable avec l'ID : " + id));
        recolteRepository.delete(recolte);
    }

    private boolean canHarvest(UUID champId , LocalDate recolteDate) {
        Optional<Recolte> latestRecolte = recolteRepository.findTopByChampIdOrderByDateRecolteDesc(champId);
        if (latestRecolte.isPresent()) {
            LocalDate lastRecolteDate = latestRecolte.get().getDateRecolte();
            long monthsSinceLastRecolte = ChronoUnit.MONTHS.between(lastRecolteDate, recolteDate);
            return monthsSinceLastRecolte >= MOIS_LIMIT;
        }
        return true;
    }



    private Double calculateTotalQuantite(Champ champ) {
        return champ.getArbres()
                .stream()
                .mapToDouble(arbre -> Optional.ofNullable(arbreService.productivite(arbre.getId()))
                        .orElseThrow(() -> new RuntimeException("Impossible de calculer la productivité pour l'arbre avec ID: " + arbre.getId())))
                .sum();
    }

    private Saison determineCurrentSeason() {
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

    private void saveArbreDetails(Champ champ, Recolte savedRecolte) {
        champ.getArbres().forEach(arbre -> {
            DetailRecolteRequestDTO detailRecolteRequestDTO = new DetailRecolteRequestDTO(
                    arbre.getId(),
                    savedRecolte.getId(),
                    arbreService.productivite(arbre.getId())
            );
            detailRecolteService.save(detailRecolteRequestDTO);
        });
    }
}
