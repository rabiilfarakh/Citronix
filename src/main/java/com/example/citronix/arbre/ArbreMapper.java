package com.example.citronix.arbre;

import com.example.citronix.arbre.dto.ArbreRequestDTO;
import com.example.citronix.arbre.dto.ArbreResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArbreMapper {
    ArbreResponseDTO toResponseDTO(Arbre arbre);
    Arbre toEntity(ArbreRequestDTO arbreRequestDTO);
}
