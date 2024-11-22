package com.example.citronix.champ.service;

import com.example.citronix.champ.Champ;
import com.example.citronix.champ.dto.request.ChampRequestDTO;
import com.example.citronix.champ.dto.response.ChampDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChampService {
    ChampDTO save(ChampRequestDTO champRequestDTO);

    ChampDTO update(UUID id, ChampRequestDTO champRequestDTO);

    Optional<ChampDTO> findById(UUID id);

    List<ChampDTO> findAll();

    void delete(UUID id);

    Double sommeSuperficies(List<Champ> champ);

    Champ findChampById(UUID id);

}
