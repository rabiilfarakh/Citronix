package com.example.citronix.vente.service;

import com.example.citronix.recolte.Recolte;
import com.example.citronix.recolte.RecolteRepository;
import com.example.citronix.vente.Vente;
import com.example.citronix.vente.VenteMapper;
import com.example.citronix.vente.VenteRepository;
import com.example.citronix.vente.dto.request.VenteRequestDTO;
import com.example.citronix.vente.dto.response.VenteResponseDTO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class VenteServiceImpl implements VenteService {

    private final VenteRepository venteRepository;
    private final RecolteRepository recolteRepository;
    private final VenteMapper venteMapper;

    @Override
    @Transactional
    public VenteResponseDTO save(VenteRequestDTO venteRequestDTO) {
        Recolte recolte = recolteRepository.findById(venteRequestDTO.recolte_id())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Récolte introuvable avec l'ID : " + venteRequestDTO.recolte_id()));

        if (recolte.getQuantite() < venteRequestDTO.quantite()) {
            throw new IllegalArgumentException("Quantité insuffisante. Disponible : "
                    + recolte.getQuantite() + ", demandée : " + venteRequestDTO.quantite());
        }

        recolte.setQuantite(recolte.getQuantite() - venteRequestDTO.quantite());
        recolteRepository.save(recolte);

        Vente vente = venteMapper.toEntity(venteRequestDTO);
        vente.setPrix(venteRequestDTO.prix() * venteRequestDTO.quantite());
        vente.setRecolte(recolte);
        vente = venteRepository.save(vente);

        return venteMapper.toDTO(vente);
    }

    @Override
    @Transactional
    public VenteResponseDTO update(UUID id, VenteRequestDTO venteRequestDTO) {
        Vente existingVente = venteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vente introuvable avec l'ID : " + id));

        Recolte recolte = recolteRepository.findById(venteRequestDTO.recolte_id())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Récolte introuvable avec l'ID : " + venteRequestDTO.recolte_id()));

        double differenceQuantite = venteRequestDTO.quantite() - existingVente.getQuantite();

        if (recolte.getQuantite() < differenceQuantite) {
            throw new IllegalArgumentException("Quantité insuffisante. Disponible : "
                    + recolte.getQuantite() + ", demandée en supplément : " + differenceQuantite);
        }

        recolte.setQuantite(recolte.getQuantite() - differenceQuantite);
        recolteRepository.save(recolte);

        existingVente.setQuantite(venteRequestDTO.quantite());
        existingVente.setPrix(venteRequestDTO.prix() * venteRequestDTO.quantite());
        existingVente.setRecolte(recolte);

        Vente updatedVente = venteRepository.save(existingVente);
        return venteMapper.toDTO(updatedVente);
    }

    @Override
    public Optional<VenteResponseDTO> findById(UUID id) {
        return venteRepository.findById(id)
                .map(venteMapper::toDTO);
    }

    @Override
    public List<VenteResponseDTO> findAll() {
        return venteRepository.findAll()
                .stream()
                .map(venteMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Vente vente = venteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vente introuvable avec l'ID : " + id));

        Recolte recolte = vente.getRecolte();
        recolte.setQuantite(recolte.getQuantite() + vente.getQuantite());
        recolteRepository.save(recolte);

        venteRepository.deleteById(id);
    }
}
