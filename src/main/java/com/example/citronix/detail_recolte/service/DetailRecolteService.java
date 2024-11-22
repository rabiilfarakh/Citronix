package com.example.citronix.detail_recolte.service;



import com.example.citronix.detail_recolte.dto.request.DetailRecolteRequestDTO;
import com.example.citronix.detail_recolte.dto.response.DetailRecolteResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DetailRecolteService {
    DetailRecolteResponseDTO save(DetailRecolteRequestDTO detailRecolteRequestDTO);

    DetailRecolteResponseDTO update(UUID id, DetailRecolteRequestDTO detailRecolteRequestDTO);

    Optional<DetailRecolteResponseDTO> findById(UUID id);

    List<DetailRecolteResponseDTO> findAll();

    void delete(UUID id);
}
