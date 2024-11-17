package com.example.citronix.champ.service;

import com.example.citronix.champ.Champ;
import com.example.citronix.champ.ChampMapper;
import com.example.citronix.champ.ChampRepository;
import com.example.citronix.champ.dto.ChampRequestDTO;
import com.example.citronix.champ.dto.ChampResponseDTO;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ChampServiceImpl implements ChampService{

    private final ChampMapper champMapper;
    private final ChampRepository champRepository;

    public ChampServiceImpl(ChampMapper champMapper, ChampRepository champRepository) {
        this.champMapper = champMapper;
        this.champRepository = champRepository;
    }

    @Override
    public ChampResponseDTO save(ChampRequestDTO champRequestDTO) {
        Champ champ = champMapper.toEntity(champRequestDTO);
        champ = champRepository.save(champ);
        return champMapper.toResponseDTO(champ);
    }

    @Override
    public Optional<ChampResponseDTO> findById(UUID id) {
        Optional<Champ> champOptional = champRepository.findById(id);
        if (champOptional.isPresent()) {
            ChampResponseDTO champResponseDTO = champMapper.toResponseDTO(champOptional.get());
            return Optional.of(champResponseDTO);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<ChampResponseDTO> findAll() {
        return champRepository.findAll().stream()
                .map(champMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        champRepository.deleteById(id);
    }
}
