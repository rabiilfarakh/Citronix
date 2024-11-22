package com.example.citronix.arbre.service;

import com.example.citronix.arbre.Arbre;
import com.example.citronix.arbre.ArbreMapper;
import com.example.citronix.arbre.ArbreRepository;
import com.example.citronix.arbre.dto.request.ArbreRequestDTO;
import com.example.citronix.arbre.dto.response.ArbreResponseDTO;
import com.example.citronix.champ.Champ;
import com.example.citronix.champ.service.ChampService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ArbreServiceImpl implements ArbreService {

    private final ArbreRepository arbreRepository;
    private final ArbreMapper arbreMapper;
    private final ChampService champService;

    @Override
    public ArbreResponseDTO save(ArbreRequestDTO arbreRequestDTO) {
        Arbre arbre = arbreMapper.toEntity(arbreRequestDTO);
        Champ champ = champService.findChampById(arbreRequestDTO.champ_id());
        arbre.setChamp(champ);
        arbre = arbreRepository.save(arbre);
        return arbreMapper.toResponseDTO(arbre);
    }

    @Override
    public ArbreResponseDTO update(UUID id, ArbreRequestDTO arbreRequestDTO) {
        Arbre existingArbre = arbreRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Arbre avec l'ID " + id + " introuvable"));

        Arbre updatedArbre = arbreMapper.toEntity(arbreRequestDTO);
        updatedArbre.setId(existingArbre.getId());

        arbreRepository.save(updatedArbre);

        return arbreMapper.toResponseDTO(updatedArbre);
    }

    @Override
    public Optional<ArbreResponseDTO> findById(UUID id) {
        return arbreRepository.findById(id)
                .map(arbreMapper::toResponseDTO);
    }

    @Override
    public List<ArbreResponseDTO> findAll() {
        return arbreRepository.findAll().stream()
                .map(arbreMapper::toResponseDTO)
                .toList();
    }

    @Override
    public void delete(UUID id) {
        if (!arbreRepository.existsById(id)) {
            throw new IllegalArgumentException("Arbre avec l'ID " + id + " introuvable");
        }
        arbreRepository.deleteById(id);
    }

    @Override
    public int age(UUID id) {
        Arbre arbre = arbreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Arbre with id: " + id + " not found"));

        LocalDate dateActuelle = LocalDate.now();
        LocalDate datePlantation = arbre.getDatePlantation();

        if (datePlantation == null) {
            throw new RuntimeException("Date de plantation manquante pour l'arbre avec id: " + id);
        }

        return Period.between(datePlantation, dateActuelle).getYears();
    }

    @Override
    public Double productivite(UUID id) {
        int age = age(id);

        if (age < 3) {
            return 2.5;
        } else if (age >= 3 && age < 10) {
            return 12.0;
        } else {
            return 20.0;
        }
    }


}
