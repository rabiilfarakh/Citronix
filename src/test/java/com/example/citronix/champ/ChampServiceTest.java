package com.example.citronix.champ.service;

import com.example.citronix.champ.Champ;
import com.example.citronix.champ.ChampMapper;
import com.example.citronix.champ.ChampRepository;
import com.example.citronix.champ.dto.request.ChampRequestDTO;
import com.example.citronix.champ.dto.response.ChampResponseDTO;
import com.example.citronix.ferme.Ferme;
import com.example.citronix.ferme.service.FermeService;
import com.example.citronix.champ.validation.ChampValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ChampServiceImplTest {

    @Mock
    private ChampMapper champMapper;
    @Mock
    private ChampRepository champRepository;
    @Mock
    private FermeService fermeService;
    @Mock
    private ChampValidator champValidator;

    @InjectMocks
    private ChampServiceImpl champService;

    private ChampRequestDTO champRequestDTO;
    private Champ champ;
    private Ferme ferme;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        champRequestDTO = new ChampRequestDTO("Champ 1", 10.0, UUID.randomUUID());
        champ = new Champ(UUID.randomUUID(), "Champ 1", 10.0, null);
        ferme = new Ferme(UUID.randomUUID(), "Ferme 1");
        champ.setFerme(ferme);
    }

    @Test
    void save_ShouldSaveChampAndReturnResponseDTO() {
        // Arrange
        when(fermeService.findFermeById(any(UUID.class))).thenReturn(ferme);
        when(champMapper.toEntity(any(ChampRequestDTO.class))).thenReturn(champ);
        when(champRepository.save(any(Champ.class))).thenReturn(champ);
        when(champMapper.toResponseDTO(any(Champ.class))).thenReturn(new ChampResponseDTO(champ.getId(), champ.getNom(), champ.getSuperficie(), null, null));

        // Act
        ChampResponseDTO result = champService.save(champRequestDTO);

        // Assert
        assertNotNull(result);
        assertEquals(champ.getNom(), result.nom());
        verify(champRepository, times(1)).save(champ);
    }

    @Test
    void update_ShouldUpdateChampAndReturnResponseDTO() {
        // Arrange
        UUID champId = UUID.randomUUID();
        when(champRepository.findById(champId)).thenReturn(Optional.of(champ));
        when(fermeService.findFermeById(any(UUID.class))).thenReturn(ferme);
        when(champMapper.toResponseDTO(any(Champ.class))).thenReturn(new ChampResponseDTO(champ.getId(), champ.getNom(), champ.getSuperficie(), null, null));

        ChampRequestDTO updateDTO = new ChampRequestDTO("Champ Updated", 20.0, champRequestDTO.ferme_id());

        // Act
        ChampResponseDTO result = champService.update(champId, updateDTO);

        // Assert
        assertNotNull(result);
        assertEquals(updateDTO.nom(), result.nom());
        assertEquals(updateDTO.superficie(), result.superficie());
        verify(champRepository, times(1)).save(champ);
    }

    @Test
    void findById_ShouldReturnChampResponseDTO() {
        // Arrange
        UUID champId = UUID.randomUUID();
        when(champRepository.findById(champId)).thenReturn(Optional.of(champ));
        when(champMapper.toResponseDTO(any(Champ.class))).thenReturn(new ChampResponseDTO(champ.getId(), champ.getNom(), champ.getSuperficie(), null, null));

        // Act
        Optional<ChampResponseDTO> result = champService.findById(champId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(champ.getNom(), result.get().nom());
    }

    @Test
    void findById_ShouldReturnEmptyOptionalWhenChampNotFound() {
        // Arrange
        UUID champId = UUID.randomUUID();
        when(champRepository.findById(champId)).thenReturn(Optional.empty());

        // Act
        Optional<ChampResponseDTO> result = champService.findById(champId);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void delete_ShouldDeleteChamp() {
        // Arrange
        UUID champId = UUID.randomUUID();
        doNothing().when(champRepository).deleteById(champId);

        // Act
        champService.delete(champId);

        // Assert
        verify(champRepository, times(1)).deleteById(champId);
    }

    @Test
    void sommeSuperficies_ShouldReturnSumOfSuperficies() {
        // Arrange
        ChampRequestDTO champDTO1 = new ChampRequestDTO("Champ 1", 10.0, champRequestDTO.ferme_id());
        ChampRequestDTO champDTO2 = new ChampRequestDTO("Champ 2", 15.0, champRequestDTO.ferme_id());

        // Act
        Double result = champService.sommeSuperficies(List.of(champDTO1, champDTO2));

        // Assert
        assertEquals(25.0, result);
    }

    @Test
    void findChampById_ShouldThrowExceptionWhenNotFound() {
        // Arrange
        UUID champId = UUID.randomUUID();
        when(champRepository.findById(champId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> champService.findChampById(champId));
    }
}
