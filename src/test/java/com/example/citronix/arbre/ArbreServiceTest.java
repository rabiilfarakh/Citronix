//package com.example.citronix.arbre;
//
//import com.example.citronix.arbre.dto.request.ArbreRequestDTO;
//import com.example.citronix.arbre.dto.response.ArbreResponseDTO;
//import com.example.citronix.arbre.service.ArbreServiceImpl;
//import com.example.citronix.champ.Champ;
//import com.example.citronix.champ.dto.response.EmbeddedChampResponse;
//import com.example.citronix.ferme.dto.response.EmbeddedFermeResponse;
//import com.example.citronix.champ.service.ChampService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//class ArbreServiceTest {
//
//    @InjectMocks
//    private ArbreServiceImpl arbreService;
//
//    @Mock
//    private ArbreRepository arbreRepository;
//
//    @Mock
//    private ArbreMapper arbreMapper;
//
//    @Mock
//    private ChampService champService;
//
//    private ArbreRequestDTO arbreRequestDTO;
//    private Arbre arbre;
//    private ArbreResponseDTO arbreResponseDTO;
//    private Champ champ;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        champ = new Champ();
//        champ.setId(UUID.randomUUID());
//        champ.setNom("Champ1");
//        champ.setSuperficie(100.0);
//
//        EmbeddedFermeResponse fermeResponse = new EmbeddedFermeResponse(
//                UUID.randomUUID(),
//                "Ferme1",
//                "Localisation1",
//                500.0,
//                new Date()
//        );
//
//        EmbeddedChampResponse champResponse = new EmbeddedChampResponse(
//                champ.getId(),
//                champ.getNom(),
//                champ.getSuperficie(),
//                fermeResponse
//        );
//        arbreRequestDTO = new ArbreRequestDTO(new Date(), champ.getId());
//
//        arbre = new Arbre();
//        arbre.setId(UUID.randomUUID());
//        arbre.setDatePlantation(new Date());
//        arbre.setChamp(champ);
//
//        arbreResponseDTO = new ArbreResponseDTO(
//                arbre.getId(),
//                arbre.getDatePlantation(),
//                champResponse
//        );
//    }

//    @Test
//    void testSave() {
//
//        when(champService.findChampById(arbreRequestDTO.champ_id())).thenReturn(champ);
//        when(arbreRepository.save(any(Arbre.class))).thenReturn(arbre);
//        when(arbreMapper.toResponseDTO(any(Arbre.class))).thenReturn(arbreResponseDTO);
//        ArbreResponseDTO result = arbreService.save(arbreRequestDTO);
//        assertNotNull(result);
//        assertEquals(arbre.getId(), result.id());
//        assertEquals(arbre.getDatePlantation(), result.datePlantation());
//        assertEquals(champ.getNom(), result.champ().nom());
//
//        verify(arbreRepository, times(1)).save(any(Arbre.class));
//    }



//    @Test
//    void testUpdate() {
//        UUID arbreId = UUID.randomUUID();
//        when(arbreRepository.findById(arbreId)).thenReturn(Optional.of(arbre));
//        when(arbreRepository.save(any(Arbre.class))).thenReturn(arbre);
//        when(arbreMapper.toResponseDTO(any(Arbre.class))).thenReturn(arbreResponseDTO);
//
//        ArbreRequestDTO updateDTO = new ArbreRequestDTO(new Date(), champ.getId());
//
//        ArbreResponseDTO result = arbreService.update(arbreId, updateDTO);
//
//        assertNotNull(result);
//        assertEquals(arbreId, result.id());
//        verify(arbreRepository, times(1)).save(any(Arbre.class));
//    }

//    @Test
//    void testFindById() {
//        UUID arbreId = UUID.randomUUID();
//        when(arbreRepository.findById(arbreId)).thenReturn(Optional.of(arbre));
//        when(arbreMapper.toResponseDTO(any(Arbre.class))).thenReturn(arbreResponseDTO);
//        Optional<ArbreResponseDTO> result = arbreService.findById(arbreId);
//
//        assertTrue(result.isPresent());
//        assertEquals(arbreId, result.get().id());
//        verify(arbreRepository, times(1)).findById(arbreId);
//    }
//
//    @Test
//    void testFindAll() {
//        when(arbreRepository.findAll()).thenReturn(List.of(arbre));
//        when(arbreMapper.toResponseDTO(any(Arbre.class))).thenReturn(arbreResponseDTO);
//
//        List<ArbreResponseDTO> result = arbreService.findAll();
//
//        assertNotNull(result);
//        assertFalse(result.isEmpty());
//        assertEquals(1, result.size());
//    }
//
//    @Test
//    void testDelete() {
//        UUID arbreId = UUID.randomUUID();
//        when(arbreRepository.existsById(arbreId)).thenReturn(true);
//
//        arbreService.delete(arbreId);
//
//        verify(arbreRepository, times(1)).deleteById(arbreId);
//    }
//
//    @Test
//    void testDeleteNotFound() {
//        UUID arbreId = UUID.randomUUID();
//        when(arbreRepository.existsById(arbreId)).thenReturn(false);
//
//        assertThrows(IllegalArgumentException.class, () -> arbreService.delete(arbreId));
//    }
//}
