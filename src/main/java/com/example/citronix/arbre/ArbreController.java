package com.example.citronix.arbre.controller;

import com.example.citronix.arbre.dto.ArbreRequestDTO;
import com.example.citronix.arbre.dto.ArbreResponseDTO;
import com.example.citronix.arbre.service.ArbreService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/arbres")
@Validated
public class ArbreController {

    private final ArbreService arbreService;

    public ArbreController(ArbreService arbreService) {
        this.arbreService = arbreService;
    }

    @GetMapping
    public ResponseEntity<List<ArbreResponseDTO>> getAllArbres() {
        List<ArbreResponseDTO> arbres = arbreService.findAll();
        return new ResponseEntity<>(arbres, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArbreResponseDTO> getArbreById(@PathVariable UUID id) {
        return arbreService.findById(id)
                .map(arbre -> new ResponseEntity<>(arbre, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ArbreResponseDTO> createArbre(@Valid @RequestBody ArbreRequestDTO arbreRequestDTO) {
        ArbreResponseDTO arbreResponseDTO = arbreService.save(arbreRequestDTO);
        return new ResponseEntity<>(arbreResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArbreResponseDTO> updateArbre(@PathVariable UUID id, @Valid @RequestBody ArbreRequestDTO arbreRequestDTO) {
        try {
            ArbreResponseDTO arbreResponseDTO = arbreService.update(id, arbreRequestDTO);
            return new ResponseEntity<>(arbreResponseDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArbre(@PathVariable UUID id) {
        try {
            arbreService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
