package com.example.citronix.champ.service;

import com.example.citronix.champ.Champ;
import com.example.citronix.champ.ChampMapper;
import com.example.citronix.champ.ChampRepository;
import com.example.citronix.champ.dto.request.ChampRequestDTO;
import com.example.citronix.champ.dto.response.ChampDTO;
import com.example.citronix.ferme.Ferme;
import com.example.citronix.ferme.service.FermeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChampServiceImpl implements ChampService {

    private final ChampMapper champMapper;
    private final ChampRepository champRepository;
    private final FermeService fermeService;

    private static final String CHAMP_NOT_FOUND = "Champ avec l'ID %s introuvable.";
    private static final String SUPERFICIE_MIN_ERROR = "La superficie d'un champ doit être d'au moins 0.1 hectare.";
    private static final String SUPERFICIE_MAX_ERROR = "La superficie d'un champ ne peut pas dépasser 50%% de la superficie totale de la ferme.";
    private static final String MAX_CHAMPS_ERROR = "Une ferme ne peut contenir plus de 10 champs.";
    private static final String INSUFFICIENT_CAPACITY_ERROR = "La capacité de superficie de la ferme est insuffisante.";

    @Override
    public ChampDTO save(ChampRequestDTO champRequestDTO) {
        Champ champ = champMapper.toEntity(champRequestDTO);
        Ferme ferme = fermeService.findFermeById(champRequestDTO.ferme_id());

        validateChamp(champRequestDTO, ferme);

        champ.setFerme(ferme);
        Champ savedChamp = champRepository.save(champ);

        return champMapper.toDTO(savedChamp);
    }

    @Transactional
    @Override
    public ChampDTO update(UUID id, ChampRequestDTO champRequestDTO) {
        Champ existingChamp = findChampById(id);
        Ferme ferme = fermeService.findFermeById(champRequestDTO.ferme_id());

        validateChamp(champRequestDTO, ferme);

        updateChampDetails(existingChamp, champRequestDTO, ferme);
        Champ updatedChamp = champRepository.save(existingChamp);

        return champMapper.toDTO(updatedChamp);
    }

    @Override
    public Optional<ChampDTO> findById(UUID id) {
        return champRepository.findById(id).map(champMapper::toDTO);
    }

    @Override
    public List<ChampDTO> findAll() {
        return champRepository.findAll().stream()
                .map(champMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        champRepository.deleteById(id);
    }

    @Override
    public Double sommeSuperficies(List<Champ> champs) {
        return champs.stream().mapToDouble(Champ::getSuperficie).sum();
    }

    @Override
    public Champ findChampById(UUID id) {
        return champRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format(CHAMP_NOT_FOUND, id)));
    }

    private void validateChamp(ChampRequestDTO champRequestDTO, Ferme ferme) {
        if (champRequestDTO.superficie() < 0.1) {
            throw new IllegalArgumentException(SUPERFICIE_MIN_ERROR);
        }

        if (champRequestDTO.superficie() > (ferme.getSuperficie() * 0.5)) {
            throw new IllegalArgumentException(SUPERFICIE_MAX_ERROR);
        }

        long nombreChamps = champRepository.countByFermeId(champRequestDTO.ferme_id());
        if (nombreChamps >= 10) {
            throw new IllegalArgumentException(MAX_CHAMPS_ERROR);
        }

        double totalSuperficie = sommeSuperficies(ferme.getChamps()) + champRequestDTO.superficie();
        if (totalSuperficie > ferme.getSuperficie()) {
            throw new IllegalArgumentException(INSUFFICIENT_CAPACITY_ERROR);
        }
    }

    private void updateChampDetails(Champ champ, ChampRequestDTO champRequestDTO, Ferme ferme) {
        champ.setNom(champRequestDTO.nom());
        champ.setSuperficie(champRequestDTO.superficie());
        champ.setFerme(ferme);
    }
}
