package com.example.citronix.recolte;

import com.example.citronix.recolte.dto.request.RecolteRequestDTO;
import com.example.citronix.recolte.dto.response.RecolteDTO;
import com.example.citronix.recolte.dto.response.RecolteResponseDTO;
import com.example.citronix.recolte.service.RecolteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/recoltes")
public class RecolteController {

    private final RecolteService recolteService;

    @PostMapping
    public ResponseEntity<RecolteDTO> create(@Valid @RequestBody RecolteRequestDTO recolteRequestDTO) {
        RecolteDTO response = recolteService.save(recolteRequestDTO);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecolteDTO> update(@PathVariable UUID id, @Valid @RequestBody RecolteRequestDTO recolteRequestDTO) {
        RecolteDTO response = recolteService.update(id, recolteRequestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecolteResponseDTO> findById(@PathVariable UUID id) {
        return recolteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<RecolteDTO>> findAll() {
        List<RecolteDTO> recoltes = recolteService.findAll();
        return ResponseEntity.ok(recoltes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        recolteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
