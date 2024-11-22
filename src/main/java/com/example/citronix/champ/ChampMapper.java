package com.example.citronix.champ;

import com.example.citronix.champ.dto.request.ChampRequestDTO;
import com.example.citronix.champ.dto.response.ChampDTO;
import com.example.citronix.champ.dto.response.ChampResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChampMapper {
    ChampResponseDTO toResponseDTO(Champ champ);
    ChampDTO toDTO(Champ champ);
    Champ toEntity(ChampRequestDTO champRequestDTO);
}
