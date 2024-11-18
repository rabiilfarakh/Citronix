package com.example.citronix.ferme.service;

import com.example.citronix.champ.Champ;
import com.example.citronix.champ.ChampRepository;
import com.example.citronix.ferme.Ferme;
import com.example.citronix.ferme.FermeMapper;
import com.example.citronix.ferme.FermeRepository;
import com.example.citronix.ferme.dto.FermeRequestDTO;
import com.example.citronix.ferme.dto.FermeResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FermeServiceImpl implements FermeService {

    private final FermeRepository fermeRepository;
    private final ChampRepository champRepository;
    private final FermeMapper fermeMapper;

    public FermeServiceImpl(FermeRepository fermeRepository, ChampRepository champRepository, FermeMapper fermeMapper) {
        this.fermeRepository = fermeRepository;
        this.champRepository = champRepository;
        this.fermeMapper = fermeMapper;
    }

    @Transactional
    @Override
    public FermeResponseDTO save(FermeRequestDTO fermeRequestDTO) {
        Ferme ferme = fermeMapper.toEntity(fermeRequestDTO);

        if (fermeRequestDTO.champIds() != null && !fermeRequestDTO.champIds().isEmpty()) {
            List<Champ> champs = champRepository.findAllById(fermeRequestDTO.champIds());
            ferme.setChamps(champs);
        }

        ferme = fermeRepository.save(ferme);
        return fermeMapper.toResponseDTO(ferme);
    }

    @Transactional
    @Override
    public FermeResponseDTO update(UUID id, FermeRequestDTO fermeRequestDTO) {
        Ferme existingFerme = fermeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ferme avec l'ID " + id + " n'existe pas."));

        existingFerme.setNom(fermeRequestDTO.nom());
        existingFerme.setLocalisation(fermeRequestDTO.localisation());
        existingFerme.setSuperficie(fermeRequestDTO.superficie());
        existingFerme.setDateCreation(fermeRequestDTO.dateCreation());

        if (fermeRequestDTO.champIds() != null) {
            List<Champ> champs = fermeRequestDTO.champIds().stream()
                    .map(champId -> champRepository.findById(champId)
                            .orElseThrow(() -> new IllegalArgumentException("Champ avec l'ID " + champId + " introuvable")))
                    .toList();

            existingFerme.setChamps(champs);
        }

        Ferme updatedFerme = fermeRepository.save(existingFerme);
        return fermeMapper.toResponseDTO(updatedFerme);
    }

    @Override
    public Optional<FermeResponseDTO> findById(UUID id) {
        return fermeRepository.findById(id)
                .map(fermeMapper::toResponseDTO);
    }

    @Override
    public List<FermeResponseDTO> findAll() {
        return fermeRepository.findAll().stream()
                .map(fermeMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        if (!fermeRepository.existsById(id)) {
            throw new IllegalArgumentException("Ferme avec l'ID " + id + " n'existe pas.");
        }
        fermeRepository.deleteById(id);
    }
}
