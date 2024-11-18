package com.example.citronix.champ.service;

import com.example.citronix.arbre.Arbre;
import com.example.citronix.arbre.ArbreRepository;
import com.example.citronix.champ.Champ;
import com.example.citronix.champ.ChampMapper;
import com.example.citronix.champ.ChampRepository;
import com.example.citronix.champ.dto.ChampRequestDTO;
import com.example.citronix.champ.dto.ChampResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ChampServiceImpl implements ChampService {

    private final ChampMapper champMapper;
    private final ChampRepository champRepository;
    private final ArbreRepository arbreRepository;

    public ChampServiceImpl(ChampMapper champMapper, ChampRepository champRepository, ArbreRepository arbreRepository) {
        this.champMapper = champMapper;
        this.champRepository = champRepository;
        this.arbreRepository = arbreRepository;
    }

    @Transactional
    @Override
    public ChampResponseDTO save(ChampRequestDTO champRequestDTO) {
        Champ champ = champMapper.toEntity(champRequestDTO);

        if (champRequestDTO.arbreIds() != null && !champRequestDTO.arbreIds().isEmpty()) {
            List<Arbre> arbres = arbreRepository.findAllById(champRequestDTO.arbreIds());
            champ.setArbres(arbres);
        }

        champ = champRepository.save(champ);
        return champMapper.toResponseDTO(champ);
    }

    @Transactional
    @Override
    public ChampResponseDTO update(UUID id, ChampRequestDTO champRequestDTO) {
        Champ existingChamp = champRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Champ avec l'ID " + id + " introuvable"));

        existingChamp.setNom(champRequestDTO.nom());
        existingChamp.setSuperficie(champRequestDTO.superficie());

        if (champRequestDTO.arbreIds() != null) {
            List<Arbre> arbres = champRequestDTO.arbreIds().stream()
                    .map(arbreId -> arbreRepository.findById(arbreId)
                            .orElseThrow(() -> new IllegalArgumentException("Arbre avec l'ID " + arbreId + " introuvable")))
                    .toList();

            existingChamp.setArbres(arbres);
        }

        Champ updatedChamp = champRepository.save(existingChamp);
        return champMapper.toResponseDTO(updatedChamp);
    }

    @Override
    public Optional<ChampResponseDTO> findById(UUID id) {
        return champRepository.findById(id)
                .map(champMapper::toResponseDTO);
    }

    @Override
    public List<ChampResponseDTO> findAll() {
        return champRepository.findAll().stream()
                .map(champMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        champRepository.deleteById(id);
    }
}