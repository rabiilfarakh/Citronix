package com.example.citronix.champ.service;

import com.example.citronix.champ.dto.ChampRequestDTO;
import com.example.citronix.champ.dto.ChampResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChampService {
    ChampResponseDTO save(ChampRequestDTO fermeRequestDTO);
    ChampResponseDTO update(UUID id, ChampRequestDTO champRequestDTO);
    Optional<ChampResponseDTO> findById(UUID id);
    List<ChampResponseDTO> findAll();
    void delete(UUID id);
}
