package com.example.citronix.arbre.service;


import com.example.citronix.arbre.dto.request.ArbreRequestDTO;
import com.example.citronix.arbre.dto.response.ArbreResponseDTO;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ArbreService {

    ArbreResponseDTO save(ArbreRequestDTO arbreRequestDTO);

    ArbreResponseDTO update(UUID id, ArbreRequestDTO arbreRequestDTO);

    Optional<ArbreResponseDTO> findById(UUID id);

    List<ArbreResponseDTO> findAll();

    void delete(UUID id);

    int age(UUID id);

    Double productivite(UUID id);
}
