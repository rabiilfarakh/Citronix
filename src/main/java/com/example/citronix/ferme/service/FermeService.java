package com.example.citronix.ferme.service;

import com.example.citronix.ferme.dto.FermeRequestDTO;
import com.example.citronix.ferme.dto.FermeResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FermeService {
    FermeResponseDTO save(FermeRequestDTO fermeRequestDTO);

    FermeResponseDTO update(UUID id, FermeRequestDTO fermeRequestDTO);

    Optional<FermeResponseDTO> findById(UUID id);

    List<FermeResponseDTO> findAll();

    void delete(UUID id);
}

