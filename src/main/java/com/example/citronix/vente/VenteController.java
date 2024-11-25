package com.example.citronix.vente;

import com.example.citronix.vente.dto.request.VenteRequestDTO;
import com.example.citronix.vente.dto.response.VenteResponseDTO;
import com.example.citronix.vente.service.VenteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/ventes")
public class VenteController {

    private final VenteService venteService;

    @PostMapping
    public ResponseEntity<VenteResponseDTO> createVente(@RequestBody VenteRequestDTO venteRequestDTO) {
        VenteResponseDTO createdVente = venteService.save(venteRequestDTO);
        return new ResponseEntity<>(createdVente, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VenteResponseDTO> updateVente(@PathVariable UUID id, @RequestBody VenteRequestDTO venteRequestDTO) {
        VenteResponseDTO updatedVente = venteService.update(id, venteRequestDTO);
        return ResponseEntity.ok(updatedVente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VenteResponseDTO> getVenteById(@PathVariable UUID id) {
        return venteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<VenteResponseDTO>> getAllVentes() {
        List<VenteResponseDTO> ventes = venteService.findAll();
        return ResponseEntity.ok(ventes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVente(@PathVariable UUID id) {
        venteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
