//package com.example.citronix.ferme;
//
//import com.example.citronix.ferme.dto.FermeRequestDTO;
//import com.example.citronix.ferme.dto.FermeResponseDTO;
//import com.example.citronix.ferme.service.FermeServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.*;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class FermeServiceTest {
//
//    @Mock
//    private FermeRepository fermeRepository;
//
//    @Mock
//    private FermeMapper fermeMapper;
//
//    @InjectMocks
//    private FermeServiceImpl fermeService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testSave() {
//        // Arrange
//        FermeRequestDTO fermeRequestDTO = new FermeRequestDTO("Ferme 1", "Localisation 1", 100.0, new Date());
//        Ferme fermeEntity = new Ferme();
//        fermeEntity.setNom(fermeRequestDTO.nom());
//        fermeEntity.setLocalisation(fermeRequestDTO.localisation());
//        fermeEntity.setSuperficie(fermeRequestDTO.superficie());
//        fermeEntity.setDateCreation(fermeRequestDTO.dateCreation());
//
//        FermeResponseDTO fermeResponseDTO = new FermeResponseDTO(fermeEntity.getId(), fermeEntity.getNom(), fermeEntity.getLocalisation(), fermeEntity.getSuperficie(), fermeEntity.getDateCreation());
//
//        when(fermeMapper.toEntity(fermeRequestDTO)).thenReturn(fermeEntity);
//        when(fermeRepository.save(fermeEntity)).thenReturn(fermeEntity);
//        when(fermeMapper.toResponseDTO(fermeEntity)).thenReturn(fermeResponseDTO);
//
//        FermeResponseDTO result = fermeService.save(fermeRequestDTO);
//
//        assertNotNull(result);
//        assertEquals(fermeResponseDTO.nom(), result.nom());
//        verify(fermeRepository, times(1)).save(fermeEntity);
//    }
//
//    @Test
//    void testFindById_NotFound() {
//        UUID fermeId = UUID.randomUUID();
//        when(fermeRepository.findById(fermeId)).thenReturn(Optional.empty());
//        Optional<FermeResponseDTO> result = fermeService.findById(fermeId);
//        assertFalse(result.isPresent());
//    }
//
//    @Test
//    void testFindAll() {
//        List<Ferme> fermeList = new ArrayList<>();
//        Ferme ferme1 = new Ferme();
//        ferme1.setNom("Ferme 1");
//        ferme1.setLocalisation("Localisation 1");
//        ferme1.setSuperficie(100.0);
//        ferme1.setDateCreation(new Date());
//        fermeList.add(ferme1);
//
//        FermeResponseDTO fermeResponseDTO = new FermeResponseDTO(ferme1.getId(), ferme1.getNom(), ferme1.getLocalisation(), ferme1.getSuperficie(), ferme1.getDateCreation());
//
//        when(fermeRepository.findAll()).thenReturn(fermeList);
//        when(fermeMapper.toResponseDTO(ferme1)).thenReturn(fermeResponseDTO);
//
//        List<FermeResponseDTO> result = fermeService.findAll();
//        assertEquals(1, result.size());
//        assertEquals(fermeResponseDTO, result.get(0));
//    }
//
//    @Test
//    void testDelete() {
//        UUID fermeId = UUID.randomUUID();
//        fermeService.delete(fermeId);
//        verify(fermeRepository, times(1)).deleteById(fermeId);
//    }
//
//}
