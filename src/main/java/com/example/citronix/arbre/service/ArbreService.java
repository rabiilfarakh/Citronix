package com.example.citronix.arbre.service;


import com.example.citronix.arbre.dto.request.ArbreRequestDTO;
import com.example.citronix.arbre.dto.response.ArbreDTO;
import com.example.citronix.arbre.dto.response.ArbreResponseDTO;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ArbreService {

    ArbreDTO save(ArbreRequestDTO arbreRequestDTO);

    ArbreDTO update(UUID id, ArbreRequestDTO arbreRequestDTO);

    Optional<ArbreDTO> findById(UUID id);

    List<ArbreDTO> findAll();

    void delete(UUID id);

    int age(UUID id);

    Double productivite(UUID id);
}
