package com.example.citronix.arbre;

import com.example.citronix.arbre.dto.request.ArbreRequestDTO;
import com.example.citronix.arbre.dto.response.ArbreDTO;
import com.example.citronix.arbre.dto.response.ArbreResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArbreMapper {
    ArbreDTO toResponseDTO(Arbre arbre);
    Arbre toEntity(ArbreRequestDTO arbreRequestDTO);
}
