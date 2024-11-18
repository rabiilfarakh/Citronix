package com.example.citronix.arbre.service;

import com.example.citronix.arbre.Arbre;
import com.example.citronix.arbre.ArbreMapper;
import com.example.citronix.arbre.ArbreRepository;
import com.example.citronix.arbre.dto.ArbreRequestDTO;
import com.example.citronix.arbre.dto.ArbreResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArbreServiceImpl implements ArbreService {

    private final ArbreRepository arbreRepository;
    private final ArbreMapper arbreMapper;

    public ArbreServiceImpl(ArbreRepository arbreRepository, ArbreMapper arbreMapper) {
        this.arbreRepository = arbreRepository;
        this.arbreMapper = arbreMapper;
    }

    @Override
    public ArbreResponseDTO save(ArbreRequestDTO arbreRequestDTO) {
        Arbre arbre = arbreMapper.toEntity(arbreRequestDTO);
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

}
