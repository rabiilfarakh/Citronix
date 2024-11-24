package com.example.citronix.vente;

import com.example.citronix.vente.dto.request.VenteRequestDTO;
import com.example.citronix.vente.dto.response.VenteResponseDTO;
import com.example.citronix.vente.service.VenteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/ventes")
public class VenteController {

    private final VenteService venteService;

    @PostMapping
    public ResponseEntity<VenteResponseDTO> saveVente(@Valid @RequestBody VenteRequestDTO venteRequestDTO) {
        VenteResponseDTO savedVente = venteService.save(venteRequestDTO);
        return new ResponseEntity<>(savedVente, HttpStatus.CREATED);
    }
}
