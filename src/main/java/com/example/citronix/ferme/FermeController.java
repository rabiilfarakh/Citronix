package com.example.citronix.ferme;

import com.example.citronix.ferme.dto.FermeRequestDTO;
import com.example.citronix.ferme.dto.FermeResponseDTO;
import com.example.citronix.ferme.service.FermeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/fermes")
@Validated
public class FermeController {

    private final FermeService fermeService;

    public FermeController(FermeService fermeService) {
        this.fermeService = fermeService;
    }

    @GetMapping
    public ResponseEntity<List<FermeResponseDTO>> getAllFermes() {
        List<FermeResponseDTO> fermes = fermeService.findAll();
        return new ResponseEntity<>(fermes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FermeResponseDTO> getFermeById(@PathVariable UUID id) {
        return fermeService.findById(id)
                .map(ferme -> new ResponseEntity<>(ferme, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<FermeResponseDTO> createFerme(@Valid @RequestBody FermeRequestDTO fermeRequestDTO) {
        FermeResponseDTO fermeResponseDTO = fermeService.save(fermeRequestDTO);
        return new ResponseEntity<>(fermeResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FermeResponseDTO> updateFerme(@PathVariable UUID id, @Valid @RequestBody FermeRequestDTO fermeRequestDTO) {
        try {
            FermeResponseDTO fermeResponseDTO = fermeService.update(id, fermeRequestDTO);
            return new ResponseEntity<>(fermeResponseDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFerme(@PathVariable UUID id) {
        try {
            fermeService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
