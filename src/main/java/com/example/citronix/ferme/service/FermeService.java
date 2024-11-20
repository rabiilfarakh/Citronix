package com.example.citronix.ferme.service;

import com.example.citronix.ferme.Ferme;
import com.example.citronix.ferme.dto.request.FermeRequestDTO;
import com.example.citronix.ferme.dto.response.FermeResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FermeService {
    FermeResponseDTO save(FermeRequestDTO fermeRequestDTO);

    FermeResponseDTO update(UUID id, FermeRequestDTO fermeRequestDTO);

    Optional<FermeResponseDTO> findById(UUID id);

    List<FermeResponseDTO> findAll();

    void delete(UUID id);

    List<FermeResponseDTO> search(FermeRequestDTO criteria);

    Ferme findFermeById(UUID id);

}

