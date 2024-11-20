package com.example.citronix.champ.service;

import com.example.citronix.champ.Champ;
import com.example.citronix.champ.ChampMapper;
import com.example.citronix.champ.ChampRepository;
import com.example.citronix.champ.dto.request.ChampRequestDTO;
import com.example.citronix.champ.dto.response.ChampResponseDTO;
import com.example.citronix.champ.validation.ChampValidator;
import com.example.citronix.champ.validation.ChampValidatorImpl;
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
    private final ChampValidator champValidator;


    @Override
    public ChampResponseDTO save(ChampRequestDTO champRequestDTO) {

        Champ champ = champMapper.toEntity(champRequestDTO);
        Ferme ferme = fermeService.findFermeById(champRequestDTO.ferme_id());

        champ.setFerme(ferme);

        champValidator.validate(champRequestDTO,ferme);

        champ = champRepository.save(champ);
        return champMapper.toResponseDTO(champ);
    }

    @Transactional
    @Override
    public ChampResponseDTO update(UUID id, ChampRequestDTO champRequestDTO) {

        Champ existingChamp = champRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Champ avec l'ID " + id + " introuvable"));

        Ferme ferme = fermeService.findFermeById(champRequestDTO.ferme_id());
        champValidator.validate(champRequestDTO, ferme);

        existingChamp.setNom(champRequestDTO.nom());
        existingChamp.setSuperficie(champRequestDTO.superficie());
        existingChamp.setFerme(ferme);

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

        @Override
        public Double sommeSuperficies(List<ChampRequestDTO> champs) {
            return champs.stream().mapToDouble(ChampRequestDTO::superficie).sum();
        }

    @Override
    public Champ findChampById(UUID id) {
        return champRepository.findById(id).orElseThrow(()-> new RuntimeException("champ with id : " + id + "not found"));
    }
}