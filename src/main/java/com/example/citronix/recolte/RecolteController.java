package com.example.citronix.recolte;

import com.example.citronix.recolte.dto.request.RecolteRequestDTO;
import com.example.citronix.recolte.dto.response.RecolteDTO;
import com.example.citronix.recolte.service.RecolteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<RecolteDTO> create(@RequestBody RecolteRequestDTO recolteRequestDTO) {
        RecolteDTO recolteDTO = recolteService.save(recolteRequestDTO);
        return new ResponseEntity<>(recolteDTO, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<RecolteDTO> update(@PathVariable UUID id, @Valid @RequestBody RecolteRequestDTO recolteRequestDTO) {
        RecolteDTO response = recolteService.update(id, recolteRequestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecolteDTO> findById(@PathVariable UUID id) {
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
