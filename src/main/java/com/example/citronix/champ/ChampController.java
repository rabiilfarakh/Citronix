package com.example.citronix.champ;

import com.example.citronix.champ.dto.ChampRequestDTO;
import com.example.citronix.champ.dto.ChampResponseDTO;
import com.example.citronix.champ.service.ChampService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/champs")
@Validated
public class ChampController {

    private final ChampService champService;

    public ChampController(ChampService champService) {
        this.champService = champService;
    }

    @GetMapping
    public ResponseEntity<List<ChampResponseDTO>> getAllChamps() {
        List<ChampResponseDTO> champs = champService.findAll();
        return new ResponseEntity<>(champs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChampResponseDTO> getChampById(@PathVariable UUID id) {
        return champService.findById(id)
                .map(champ -> new ResponseEntity<>(champ, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ChampResponseDTO> createChamp(@Valid @RequestBody ChampRequestDTO champRequestDTO) {
        ChampResponseDTO champResponseDTO = champService.save(champRequestDTO);
        return new ResponseEntity<>(champResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChampResponseDTO> updateChamp(@PathVariable UUID id, @Valid @RequestBody ChampRequestDTO champRequestDTO) {
        try {
            ChampResponseDTO champResponseDTO = champService.update(id, champRequestDTO);
            return new ResponseEntity<>(champResponseDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChamp(@PathVariable UUID id) {
        try {
            champService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
