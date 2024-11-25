package com.example.citronix.vente.service;


import com.example.citronix.vente.dto.request.VenteRequestDTO;
import com.example.citronix.vente.dto.response.VenteResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VenteService {
    VenteResponseDTO save(VenteRequestDTO venteRequestDTO);

    VenteResponseDTO update(UUID id, VenteRequestDTO venteRequestDTO);

    Optional<VenteResponseDTO> findById(UUID id);

    List<VenteResponseDTO> findAll();

    void delete(UUID id);
}
