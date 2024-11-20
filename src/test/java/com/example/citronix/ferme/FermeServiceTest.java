//package com.example.citronix.ferme;
//
//import com.example.citronix.champ.Champ;
//import com.example.citronix.champ.ChampRepository;
//
//import com.example.citronix.ferme.dto.request.FermeRequestDTO;
//import com.example.citronix.ferme.dto.response.FermeResponseDTO;
//import com.example.citronix.ferme.service.FermeServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//
//import java.util.*;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//
//public class FermeServiceTest {
//
//    @Mock
//    private FermeRepository fermeRepository;
//
//    @Mock
//    private ChampRepository champRepository;
//
//    @Mock
//    private FermeMapper fermeMapper;
//
//    @InjectMocks
//    private FermeServiceImpl fermeService;
//
//    private FermeRequestDTO fermeRequestDTO;
//    private Ferme ferme;
//    private FermeResponseDTO fermeResponseDTO;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        UUID champId = UUID.randomUUID();
//        List<UUID> champIds = List.of(champId);
//
//        fermeRequestDTO = new FermeRequestDTO("Ferme1", "Localisation1", 100.0, new Date(), champIds);
//        ferme = new Ferme();
//        ferme.setId(UUID.randomUUID());
//        ferme.setNom("Ferme1");
//        ferme.setLocalisation("Localisation1");
//        ferme.setSuperficie(100.0);
//        ferme.setDateCreation(new Date());
//
//        champRepository = mock(ChampRepository.class);
//        Champ champ = new Champ();
//        champ.setId(champId);
//
//        when(champRepository.findAllById(champIds)).thenReturn(List.of(champ));
//
//        fermeResponseDTO = new FermeResponseDTO(ferme.getId(), ferme.getNom(), ferme.getLocalisation(),
//                ferme.getSuperficie(), ferme.getDateCreation(), Collections.emptyList());
//
//        when(fermeMapper.toEntity(fermeRequestDTO)).thenReturn(ferme);
//        when(fermeMapper.toResponseDTO(ferme)).thenReturn(fermeResponseDTO);
//    }
//
//    @Test
//    void testSaveFerme() {
//        when(fermeRepository.save(any(Ferme.class))).thenReturn(ferme);
//
//        FermeResponseDTO response = fermeService.save(fermeRequestDTO);
//
//        assertNotNull(response);
//        assertEquals("Ferme1", response.nom());
//        verify(fermeRepository, times(1)).save(any(Ferme.class));
//    }
//
//
//    @Test
//    void testFindById() {
//        UUID id = UUID.randomUUID();
//        when(fermeRepository.findById(id)).thenReturn(Optional.of(ferme));
//
//        Optional<FermeResponseDTO> foundFerme = fermeService.findById(id);
//
//        assertTrue(foundFerme.isPresent());
//        assertEquals(ferme.getNom(), foundFerme.get().nom());
//    }
//
//    @Test
//    void testFindAll() {
//        when(fermeRepository.findAll()).thenReturn(List.of(ferme));
//
//        List<FermeResponseDTO> allFermes = fermeService.findAll();
//        assertNotNull(allFermes);
//        assertFalse(allFermes.isEmpty());
//        assertEquals(1, allFermes.size());
//    }
//
//    @Test
//    void testDelete() {
//        UUID id = UUID.randomUUID();
//        when(fermeRepository.existsById(id)).thenReturn(true);
//
//        fermeService.delete(id);
//
//        verify(fermeRepository, times(1)).deleteById(id);
//    }
//
//    @Test
//    void testDeleteNotFound() {
//        UUID id = UUID.randomUUID();
//        when(fermeRepository.existsById(id)).thenReturn(false);
//
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> fermeService.delete(id));
//        assertEquals("Ferme avec l'ID " + id + " n'existe pas.", exception.getMessage());
//    }
//}
