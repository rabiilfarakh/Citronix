package com.example.citronix.ferme;

import com.example.citronix.ferme.dto.FermeRequestDTO;
import com.example.citronix.ferme.dto.FermeResponseDTO;
import com.example.citronix.ferme.service.FermeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/ferme")
public class FermeController {

    public FermeController(FermeService fermeService) {
        this.fermeService = fermeService;
    }

    private final FermeService fermeService;

    @PostMapping
    public ResponseEntity<FermeResponseDTO> createFerme(@RequestBody FermeRequestDTO fermeRequestDTO){
        FermeResponseDTO fermeResponseDTO = fermeService.save(fermeRequestDTO);
        return new ResponseEntity<>(fermeResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FermeResponseDTO> getFermeById(@PathVariable UUID id) {
        Optional<FermeResponseDTO> fermeResponseDTO = fermeService.findById(id);
        return fermeResponseDTO
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<FermeResponseDTO>> getAllFermes() {
        List<FermeResponseDTO> fermes = fermeService.findAll();
        return new ResponseEntity<>(fermes, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFerme(@PathVariable UUID id) {
        fermeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
