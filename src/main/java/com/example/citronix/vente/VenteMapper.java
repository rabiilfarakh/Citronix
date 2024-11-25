package com.example.citronix.vente;

import com.example.citronix.vente.dto.request.VenteRequestDTO;
import com.example.citronix.vente.dto.response.VenteResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VenteMapper {
    Vente toEntity(VenteRequestDTO venteRequestDTO);
    VenteResponseDTO toDTO(Vente vente);
}
