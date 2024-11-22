package com.example.citronix.detail_recolte;

import com.example.citronix.detail_recolte.dto.request.DetailRecolteRequestDTO;
import com.example.citronix.detail_recolte.dto.response.DetailRecolteResponseDTO;
import com.example.citronix.detail_recolte.service.DetailRecolteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/detail-recoltes")
public class DetailRecolteController {

    private final DetailRecolteService detailRecolteService;

    @PostMapping
    public ResponseEntity<DetailRecolteResponseDTO> createDetailRecolte(@RequestBody DetailRecolteRequestDTO requestDTO) {
        DetailRecolteResponseDTO response = detailRecolteService.save(requestDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetailRecolteResponseDTO> updateDetailRecolte(
            @PathVariable UUID id,
            @RequestBody DetailRecolteRequestDTO requestDTO) {
        DetailRecolteResponseDTO response = detailRecolteService.update(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailRecolteResponseDTO> getDetailRecolteById(@PathVariable UUID id) {
        Optional<DetailRecolteResponseDTO> response = detailRecolteService.findById(id);
        return response.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<DetailRecolteResponseDTO>> getAllDetailRecoltes() {
        List<DetailRecolteResponseDTO> responses = detailRecolteService.findAll();
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetailRecolte(@PathVariable UUID id) {
        detailRecolteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
