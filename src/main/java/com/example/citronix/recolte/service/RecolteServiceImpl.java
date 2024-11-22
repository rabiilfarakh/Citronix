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
import com.example.citronix.recolte.dto.response.RecolteResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    @Override
    public RecolteDTO save(RecolteRequestDTO recolteRequestDTO) {

        Champ champ = champService.findChampById(recolteRequestDTO.champ_id());

        if (champ.getArbres() == null || champ.getArbres().isEmpty()) {
            throw new RuntimeException("Le champ avec ID: " + champ.getId() + " ne contient aucun arbre.");
        }

        Double quantite = champ.getArbres()
                .stream()
                .mapToDouble(arbre -> Optional.ofNullable(arbreService.productivite(arbre.getId()))
                        .orElseThrow(() -> new RuntimeException("Impossible de calculer la productivité pour l'arbre avec ID: " + arbre.getId())))
                .sum();

        Recolte recolte = recolteMapper.toEntity(recolteRequestDTO);
        recolte.setQuantite(quantite);


        int mois = LocalDate.now().getMonthValue();
        Saison saisonActuelle;

        if (mois >= 3 && mois <= 5) {
            saisonActuelle = Saison.PRINTEMPS;
        } else if (mois >= 6 && mois <= 8) {
            saisonActuelle = Saison.ETE;
        } else if (mois >= 9 && mois <= 11) {
            saisonActuelle = Saison.AUTOMNE;
        } else {
            saisonActuelle = Saison.HIVER;
        }

        recolte.setSaison(saisonActuelle);

        Recolte savedRecolte = recolteRepository.save(recolte);

        champ.getArbres().forEach(arbre -> {
            DetailRecolteRequestDTO detailRecolteRequestDTO = new DetailRecolteRequestDTO(
                    arbre.getId(),
                    savedRecolte.getId(),
                    arbreService.productivite(arbre.getId())
            );
            detailRecolteService.save(detailRecolteRequestDTO);
        });

        return recolteMapper.toDTO(savedRecolte);
    }



    @Override
    public RecolteDTO update(UUID id, RecolteRequestDTO recolteRequestDTO) {
        Recolte existingRecolte = recolteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Récolte introuvable avec l'ID : " + id));

        existingRecolte.setDateRecolte(recolteRequestDTO.dateRecolte());
        Champ champ = champService.findChampById(recolteRequestDTO.champ_id());

        Double quantite = champ.getArbres()
                .stream()
                .mapToDouble(arbre -> arbreService.productivite(arbre.getId()))
                .sum();
        existingRecolte.setQuantite(quantite);

        Recolte updatedRecolte = recolteRepository.save(existingRecolte);
        return recolteMapper.toDTO(updatedRecolte);
    }

    @Override
    public Optional<RecolteResponseDTO> findById(UUID id) {
        return recolteRepository.findById(id)
                .map(recolteMapper::toResponseDTO);
    }

    @Override
    public List<RecolteDTO> findAll() {
        return recolteRepository.findAll()
                .stream()
                .map(recolteMapper::toDTO)
                .collect(Collectors.toList()).reversed();
    }

    @Override
    public void delete(UUID id) {
        Recolte recolte = recolteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Récolte introuvable avec l'ID : " + id));
        recolteRepository.delete(recolte);
    }
}
