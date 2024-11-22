package com.example.citronix.recolte.service;

import com.example.citronix.recolte.dto.request.RecolteRequestDTO;
import com.example.citronix.recolte.dto.response.RecolteDTO;
import com.example.citronix.recolte.dto.response.RecolteResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecolteService {
    RecolteDTO save(RecolteRequestDTO recolteRequestDTO);

    RecolteDTO update(UUID id, RecolteRequestDTO recolteRequestDTO);

    Optional<RecolteResponseDTO> findById(UUID id);

    List<RecolteDTO> findAll();

    void delete(UUID id);
}
