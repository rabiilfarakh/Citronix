package com.example.citronix.champ;

import com.example.citronix.champ.dto.ChampRequestDTO;
import com.example.citronix.champ.dto.ChampResponseDTO;
import com.example.citronix.champ.service.ChampService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/champ")
public class ChampController {

    private final ChampService champService;

    public ChampController(ChampService champService) {
        this.champService = champService;
    }

    @PostMapping
    public ResponseEntity<ChampResponseDTO> createChamp(@RequestBody ChampRequestDTO champRequestDTO) {
        ChampResponseDTO champResponseDTO = champService.save(champRequestDTO);
        return new ResponseEntity<>(champResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChampResponseDTO> getChampById(@PathVariable UUID id) {
        Optional<ChampResponseDTO> champResponseDTO = champService.findById(id);
        return champResponseDTO
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<ChampResponseDTO>> getAllChamps() {
        List<ChampResponseDTO> champs = champService.findAll();
        return new ResponseEntity<>(champs, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChamp(@PathVariable UUID id) {
        champService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
