package com.example.citronix.detail_recolte;

import com.example.citronix.detail_recolte.dto.request.DetailRecolteRequestDTO;
import com.example.citronix.detail_recolte.dto.response.DetailRecolteResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DetailRecolteMapper {

    DetailRecolte toEntity(DetailRecolteRequestDTO detailRecolteRequestDTO);

    DetailRecolteResponseDTO toResponseDTO(DetailRecolte detailRecolte);
}
