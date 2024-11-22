package com.example.citronix.recolte;

import com.example.citronix.recolte.dto.request.RecolteRequestDTO;
import com.example.citronix.recolte.dto.response.RecolteDTO;
import com.example.citronix.recolte.dto.response.RecolteResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecolteMapper {

    Recolte toEntity(RecolteRequestDTO recolteRequestDTO);
    RecolteDTO toDTO(Recolte recolte);
    RecolteResponseDTO toResponseDTO(Recolte recolte);
}
