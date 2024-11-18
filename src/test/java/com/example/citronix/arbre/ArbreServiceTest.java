package com.example.citronix.arbre;

import com.example.citronix.arbre.dto.ArbreRequestDTO;
import com.example.citronix.arbre.dto.ArbreResponseDTO;
import com.example.citronix.arbre.service.ArbreServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ArbreServiceTest {

    @Mock
    private ArbreRepository arbreRepository;

    @Mock
    private ArbreMapper arbreMapper;

    @InjectMocks
    private ArbreServiceImpl arbreService;

    private ArbreRequestDTO arbreRequestDTO;
    private Arbre arbre;
    private ArbreResponseDTO arbreResponseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        arbreRequestDTO = new ArbreRequestDTO(new Date());
        arbre = new Arbre();
        arbre.setId(UUID.randomUUID());
        arbre.setDatePlantation(new Date());

        arbreResponseDTO = new ArbreResponseDTO(arbre.getId(), arbre.getDatePlantation());

        when(arbreMapper.toEntity(any(ArbreRequestDTO.class))).thenReturn(arbre);
        when(arbreMapper.toResponseDTO(any(Arbre.class))).thenReturn(arbreResponseDTO);
    }

    @Test
    void testSave() {
        when(arbreRepository.save(any(Arbre.class))).thenReturn(arbre);

        ArbreResponseDTO response = arbreService.save(arbreRequestDTO);

        assertNotNull(response);
        assertEquals(arbre.getId(), response.id());
        assertEquals(arbre.getDatePlantation(), response.datePlantation());
        verify(arbreRepository, times(1)).save(any(Arbre.class));
    }


    @Test
    void testFindById() {
        UUID arbreId = UUID.randomUUID();
        when(arbreRepository.findById(arbreId)).thenReturn(Optional.of(arbre));

        Optional<ArbreResponseDTO> foundArbre = arbreService.findById(arbreId);

        assertTrue(foundArbre.isPresent());
        assertEquals(arbre.getId(), foundArbre.get().id());
    }

    @Test
    void testFindAll() {
        List<Arbre> arbres = List.of(arbre);
        when(arbreRepository.findAll()).thenReturn(arbres);

        List<ArbreResponseDTO> allArbres = arbreService.findAll();

        assertNotNull(allArbres);
        assertFalse(allArbres.isEmpty());
        assertEquals(1, allArbres.size());
    }

    @Test
    void testDelete() {
        UUID arbreId = UUID.randomUUID();
        when(arbreRepository.existsById(arbreId)).thenReturn(true);

        arbreService.delete(arbreId);

        verify(arbreRepository, times(1)).deleteById(arbreId);
    }

}
