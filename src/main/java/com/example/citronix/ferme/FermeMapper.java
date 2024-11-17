package com.example.citronix.ferme;

import com.example.citronix.ferme.dto.FermeRequestDTO;
import com.example.citronix.ferme.dto.FermeResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FermeMapper {
    FermeResponseDTO toResponseDTO(Ferme ferme);
    Ferme toEntity(FermeRequestDTO fermeRequestDTO);
}
