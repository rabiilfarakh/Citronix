package com.example.citronix.detail_recolte.service;

import com.example.citronix.arbre.Arbre;
import com.example.citronix.arbre.ArbreRepository;
import com.example.citronix.champ.Champ;
import com.example.citronix.detail_recolte.DetailRecolte;
import com.example.citronix.detail_recolte.DetailRecolteMapper;
import com.example.citronix.detail_recolte.DetailRecolteRepository;
import com.example.citronix.detail_recolte.dto.request.DetailRecolteRequestDTO;
import com.example.citronix.detail_recolte.dto.response.DetailRecolteResponseDTO;
import com.example.citronix.recolte.Recolte;
import com.example.citronix.recolte.RecolteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class DetailRecolteServiceImpl implements DetailRecolteService{

    private final DetailRecolteMapper detailRecolteMapper;
    private final DetailRecolteRepository detailRecolteRepository;
    private  final ArbreRepository arbreRepository;
    private final RecolteRepository recolteRepository;

    @Override
    public DetailRecolteResponseDTO save(DetailRecolteRequestDTO detailRecolteRequestDTO) {

        Arbre arbre = arbreRepository.findById(detailRecolteRequestDTO.arbre_id())
                .orElseThrow(() -> new RuntimeException("Arbre introuvable avec ID: " + detailRecolteRequestDTO.arbre_id()));

        Recolte recolte = recolteRepository.findById(detailRecolteRequestDTO.recolte_id())
                .orElseThrow(() -> new RuntimeException("Recolte introuvable avec ID: " + detailRecolteRequestDTO.recolte_id()));

        DetailRecolte detailRecolte = detailRecolteMapper.toEntity(detailRecolteRequestDTO);
        detailRecolte.setArbre(arbre);
        detailRecolte.setRecolte(recolte);

        detailRecolte = detailRecolteRepository.save(detailRecolte);

        return detailRecolteMapper.toResponseDTO(detailRecolte);
    }


    @Override
    public DetailRecolteResponseDTO update(UUID id, DetailRecolteRequestDTO detailRecolteRequestDTO) {

        DetailRecolte existingDetailRecolte = detailRecolteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DetailRecolte introuvable avec ID: " + id));

        Arbre arbre = arbreRepository.findById(detailRecolteRequestDTO.arbre_id())
                .orElseThrow(() -> new RuntimeException("Arbre introuvable avec ID: " + detailRecolteRequestDTO.arbre_id()));

        Recolte recolte = recolteRepository.findById(detailRecolteRequestDTO.recolte_id())
                .orElseThrow(() -> new RuntimeException("Recolte introuvable avec ID: " + detailRecolteRequestDTO.recolte_id()));

        existingDetailRecolte.setArbre(arbre);
        existingDetailRecolte.setRecolte(recolte);
        existingDetailRecolte.setQuantite(detailRecolteRequestDTO.quantite());

        existingDetailRecolte = detailRecolteRepository.save(existingDetailRecolte);

        return detailRecolteMapper.toResponseDTO(existingDetailRecolte);
    }

    @Override
    public Optional<DetailRecolteResponseDTO> findById(UUID id) {

        return detailRecolteRepository.findById(id)
                .map(detailRecolteMapper::toResponseDTO);
    }

    @Override
    public List<DetailRecolteResponseDTO> findAll() {

        return detailRecolteRepository.findAll()
                .stream()
                .map(detailRecolteMapper::toResponseDTO)
                .toList();
    }

    @Override
    public void delete(UUID id) {

        if (!detailRecolteRepository.existsById(id)) {
            throw new RuntimeException("DetailRecolte introuvable avec ID: " + id);
        }
        detailRecolteRepository.deleteById(id);
    }

}
