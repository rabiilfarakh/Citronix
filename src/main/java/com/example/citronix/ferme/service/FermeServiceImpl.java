package com.example.citronix.ferme.service;

import com.example.citronix.ferme.Ferme;
import com.example.citronix.ferme.FermeMapper;
import com.example.citronix.ferme.FermeRepository;
import com.example.citronix.ferme.dto.FermeRequestDTO;
import com.example.citronix.ferme.dto.FermeResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class FermeServiceImpl implements FermeService{

    private final FermeRepository fermeRepository;
    private final FermeMapper fermeMapper;

    public FermeServiceImpl(FermeRepository fermeRepository, FermeMapper fermeMapper) {
        this.fermeRepository = fermeRepository;
        this.fermeMapper = fermeMapper;
    }

    @Override
    public FermeResponseDTO save(FermeRequestDTO fermeRequestDTO) {
        Ferme ferme = fermeMapper.toEntity(fermeRequestDTO);
        ferme = fermeRepository.save(ferme);
        return fermeMapper.toResponseDTO(ferme);
    }

    @Override
    public Optional<FermeResponseDTO> findById(UUID id) {
        Optional<Ferme> fermeOptional = fermeRepository.findById(id);
        if (fermeOptional.isPresent()) {
            FermeResponseDTO fermeResponseDTO = fermeMapper.toResponseDTO(fermeOptional.get());
            return Optional.of(fermeResponseDTO);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<FermeResponseDTO> findAll() {
        return fermeRepository.findAll().stream()
                .map(fermeMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        fermeRepository.deleteById(id);
    }
}
