package com.example.citronix.detail_recolte;

import com.example.citronix.detail_recolte.dto.request.DetailRecolteRequestDTO;
import com.example.citronix.detail_recolte.dto.response.DetailRecolteResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DetailRecolteMapper {

    DetailRecolte toEntity(DetailRecolteRequestDTO detailRecolteRequestDTO);

    @Mapping(target = "arbre_id", source = "arbre.id")
    @Mapping(target = "recolte_id", source = "recolte.id")
    DetailRecolteResponseDTO toResponseDTO(DetailRecolte detailRecolte);
}
