package com.example.citronix.champ;

import com.example.citronix.arbre.ArbreRepository;
import com.example.citronix.champ.Champ;
import com.example.citronix.champ.ChampMapper;
import com.example.citronix.champ.ChampRepository;
import com.example.citronix.champ.dto.ChampRequestDTO;
import com.example.citronix.champ.dto.ChampResponseDTO;
import com.example.citronix.champ.service.ChampServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChampServiceImplTest {

    @Mock
    private ChampRepository champRepository;

    @Mock
    private ArbreRepository arbreRepository;

    @Mock
    private ChampMapper champMapper;

    @InjectMocks
    private ChampServiceImpl champService;

    private UUID champId;
    private Champ champ;
    private ChampRequestDTO champRequestDTO;
    private ChampResponseDTO champResponseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        champId = UUID.randomUUID();
        champ = new Champ();
        champ.setId(champId);
        champ.setNom("Champ A");
        champ.setSuperficie(10.0);

        champRequestDTO = new ChampRequestDTO("Champ A", 10.0, null);
        champResponseDTO = new ChampResponseDTO(champ.getId(), champ.getNom(), champ.getSuperficie(), null);
    }

    @Test
    void testSaveChamp() {
        when(champMapper.toEntity(champRequestDTO)).thenReturn(champ);
        when(champRepository.save(champ)).thenReturn(champ);
        when(champMapper.toResponseDTO(champ)).thenReturn(champResponseDTO);

        ChampResponseDTO result = champService.save(champRequestDTO);

        assertNotNull(result);
        assertEquals("Champ A", result.nom());
        assertEquals(10.0, result.superficie());
        verify(champRepository, times(1)).save(champ);
    }

    @Test
    void testUpdateChamp() {
        when(champRepository.findById(champId)).thenReturn(Optional.of(champ));
        when(champMapper.toResponseDTO(champ)).thenReturn(champResponseDTO);

        ChampRequestDTO updateDTO = new ChampRequestDTO("Champ B", 15.0, null);
        Champ updatedChamp = new Champ();
        updatedChamp.setId(champId);
        updatedChamp.setNom("Champ B");
        updatedChamp.setSuperficie(15.0);

        when(champMapper.toEntity(updateDTO)).thenReturn(updatedChamp);
        when(champRepository.save(updatedChamp)).thenReturn(updatedChamp);

        ChampResponseDTO result = champService.update(champId, updateDTO);

        assertNotNull(result);
        assertEquals("Champ B", result.nom());
        assertEquals(15.0, result.superficie());
        verify(champRepository, times(1)).save(updatedChamp);
    }

    @Test
    void testFindById() {
        when(champRepository.findById(champId)).thenReturn(Optional.of(champ));
        when(champMapper.toResponseDTO(champ)).thenReturn(champResponseDTO);

        Optional<ChampResponseDTO> result = champService.findById(champId);

        assertTrue(result.isPresent());
        assertEquals("Champ A", result.get().nom());
    }

    @Test
    void testFindAll() {
        List<Champ> champs = List.of(champ);
        when(champRepository.findAll()).thenReturn(champs);
        when(champMapper.toResponseDTO(champ)).thenReturn(champResponseDTO);

        List<ChampResponseDTO> result = champService.findAll();

        assertEquals(1, result.size());
        assertEquals("Champ A", result.get(0).nom());
    }

    @Test
    void testDeleteChamp() {
        champService.delete(champId);

        verify(champRepository, times(1)).deleteById(champId);
    }

    @Test
    void testUpdateChamp_NotFound() {
        UUID nonExistingId = UUID.randomUUID();
        ChampRequestDTO updateDTO = new ChampRequestDTO("Champ B", 15.0, null);
        when(champRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> champService.update(nonExistingId, updateDTO));
    }
}
