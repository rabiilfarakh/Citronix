package com.example.citronix.ferme.service;

import com.example.citronix.champ.ChampRepository;
import com.example.citronix.ferme.Ferme;
import com.example.citronix.ferme.FermeMapper;
import com.example.citronix.ferme.FermeRepository;
import com.example.citronix.ferme.dto.request.FermeRequestDTO;
import com.example.citronix.ferme.dto.response.FermeResponseDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FermeServiceImpl implements FermeService {

    private final FermeRepository fermeRepository;
    private final ChampRepository champRepository;
    private final FermeMapper fermeMapper;
    private final EntityManager entityManager;

    public FermeServiceImpl(FermeRepository fermeRepository, ChampRepository champRepository, FermeMapper fermeMapper, EntityManager entityManager) {
        this.fermeRepository = fermeRepository;
        this.champRepository = champRepository;
        this.fermeMapper = fermeMapper;
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public FermeResponseDTO save(FermeRequestDTO fermeRequestDTO) {
        Ferme ferme = fermeMapper.toEntity(fermeRequestDTO);
        ferme = fermeRepository.save(ferme);
        return fermeMapper.toResponseDTO(ferme);
    }

    @Transactional
    @Override
    public FermeResponseDTO update(UUID id, FermeRequestDTO fermeRequestDTO) {
        Ferme existingFerme = fermeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ferme avec l'ID " + id + " n'existe pas."));

        existingFerme.setNom(fermeRequestDTO.nom());
        existingFerme.setLocalisation(fermeRequestDTO.localisation());
        existingFerme.setSuperficie(fermeRequestDTO.superficie());
        existingFerme.setDateCreation(fermeRequestDTO.dateCreation());

        Ferme updatedFerme = fermeRepository.save(existingFerme);
        return fermeMapper.toResponseDTO(updatedFerme);
    }

    @Override
    public Optional<FermeResponseDTO> findById(UUID id) {
        return fermeRepository.findById(id)
                .map(fermeMapper::toResponseDTO);
    }

    @Override
    public List<FermeResponseDTO> findAll() {
        return fermeRepository.findAll().stream()
                .map(fermeMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        if (!fermeRepository.existsById(id)) {
            throw new IllegalArgumentException("Ferme avec l'ID " + id + " n'existe pas.");
        }
        fermeRepository.deleteById(id);
    }

    @Override
    public List<FermeResponseDTO> search(FermeRequestDTO criteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ferme> query = cb.createQuery(Ferme.class);
        Root<Ferme> ferme = query.from(Ferme.class);

        List<Predicate> predicates = new ArrayList<>();

        if (criteria.nom() != null && !criteria.nom().isBlank()) {
            predicates.add(cb.like(cb.lower(ferme.get("nom")), "%" + criteria.nom().toLowerCase() + "%"));
        }

        if (criteria.localisation() != null && !criteria.localisation().isBlank()) {
            predicates.add(cb.equal(ferme.get("localisation"), criteria.localisation()));
        }

        if (criteria.superficie() != null) {
            predicates.add(cb.greaterThanOrEqualTo(ferme.get("superficie"), criteria.superficie()));
        }

        if (criteria.dateCreation() != null) {
            predicates.add(cb.greaterThanOrEqualTo(ferme.get("dateCreation"), criteria.dateCreation()));
        }

        query.where(predicates.toArray(new Predicate[0]));

        List<Ferme> result = entityManager.createQuery(query).getResultList();
        return result.stream().map(fermeMapper::toResponseDTO).toList();
    }

    @Override
    public Ferme findFermeById(UUID id) {
        return fermeRepository.findById(id).orElseThrow(()-> new RuntimeException("farm with id : " + id + "not found"));
    }
}
