//package com.example.citronix.champ;
//
//import com.example.citronix.champ.dto.request.ChampRequestDTO;
//import com.example.citronix.champ.dto.response.ChampResponseDTO;
//import com.example.citronix.champ.service.ChampServiceImpl;
//import com.example.citronix.ferme.Ferme;
//import com.example.citronix.ferme.dto.response.EmbeddedFermeResponse;
//import com.example.citronix.ferme.service.FermeService;
//
//import com.example.citronix.champ.validation.ChampValidator;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//
//import java.time.LocalDate;
//import java.util.*;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ChampServiceTest {
//
//    @Mock
//    private ChampRepository champRepository;
//
//    @Mock
//    private FermeService fermeService;
//
//    @Mock
//    private ChampMapper champMapper;
//
//    @Mock
//    private ChampValidator champValidator;
//
//    @InjectMocks
//    private ChampServiceImpl champService;
//
//    private ChampRequestDTO champRequestDTO;
//    private Champ champ;
//    private ChampResponseDTO champResponseDTO;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        UUID fermeId = UUID.randomUUID();
//        champRequestDTO = new ChampRequestDTO("Champ1", 150.0, fermeId);
//
//        Ferme ferme = new Ferme();
//        ferme.setId(fermeId);
//        ferme.setNom("Ferme1");
//        ferme.setSuperficie(200.0);
//        ferme.setDateCreation(new LocalDate());
//
//        champ = new Champ();
//        champ.setId(UUID.randomUUID());
//        champ.setNom("Champ1");
//        champ.setSuperficie(150.0);
//        champ.setFerme(ferme);
//
//        EmbeddedFermeResponse fermeResponse = new EmbeddedFermeResponse(
//                ferme.getId(),
//                ferme.getNom(),
//                ferme.getLocalisation(),
//                ferme.getSuperficie(),
//                ferme.getDateCreation()
//        );
//
//        champResponseDTO = new ChampResponseDTO(
//                champ.getId(),
//                champ.getNom(),
//                champ.getSuperficie(),
//                fermeResponse,
//                Collections.emptyList()
//        );
//
//        when(fermeService.findFermeById(fermeId)).thenReturn(ferme);
//        when(champMapper.toEntity(champRequestDTO)).thenReturn(champ);
//        when(champMapper.toResponseDTO(champ)).thenReturn(champResponseDTO);
//    }
//
//    @Test
//    void testSaveChamp() {
//        when(champRepository.save(any(Champ.class))).thenReturn(champ);
//
//        ChampResponseDTO response = champService.save(champRequestDTO);
//
//        assertNotNull(response);
//        assertEquals("Champ1", response.nom());
//        verify(champRepository, times(1)).save(any(Champ.class));
//    }
//
//    @Test
//    void testUpdateChamp() {
//        UUID champId = champ.getId();
//        ChampRequestDTO champRequestDTO = new ChampRequestDTO("UpdatedChamp", 200.0, champ.getFerme().getId());
//
//        when(champRepository.findById(champId)).thenReturn(Optional.of(champ));
//        when(champRepository.save(any(Champ.class))).thenReturn(champ);
//
//        champService.update(champId, champRequestDTO);
//
//        assertEquals("UpdatedChamp", champ.getNom());
//        verify(champRepository, times(1)).save(any(Champ.class));
//    }
//
//
//    @Test
//    void testFindById() {
//        UUID champId = champ.getId();
//        when(champRepository.findById(champId)).thenReturn(Optional.of(champ));
//
//        Optional<ChampResponseDTO> foundChamp = champService.findById(champId);
//
//        assertTrue(foundChamp.isPresent());
//        assertEquals(champ.getNom(), foundChamp.get().nom());
//    }
//
//    @Test
//    void testFindAll() {
//        when(champRepository.findAll()).thenReturn(List.of(champ));
//
//        List<ChampResponseDTO> allChamps = champService.findAll();
//        assertNotNull(allChamps);
//        assertFalse(allChamps.isEmpty());
//        assertEquals(1, allChamps.size());
//    }
//
//    @Test
//    void testDelete() {
//        UUID champId = champ.getId();
//        when(champRepository.existsById(champId)).thenReturn(true);
//
//        champService.delete(champId);
//
//        verify(champRepository, times(1)).deleteById(champId);
//    }
//
//    @Test
//    void testSommeSuperficies() {
//        List<ChampRequestDTO> champs = List.of(new ChampRequestDTO("Champ1", 100.0, champRequestDTO.ferme_id()),
//                new ChampRequestDTO("Champ2", 150.0, champRequestDTO.ferme_id()));
//
//        Double sum = champService.sommeSuperficies(champs);
//        assertEquals(250.0, sum);
//    }
//
//    @Test
//    void testFindChampById() {
//        UUID champId = champ.getId();
//        when(champRepository.findById(champId)).thenReturn(Optional.of(champ));
//
//        Champ foundChamp = champService.findChampById(champId);
//
//        assertNotNull(foundChamp);
//        assertEquals(champ.getNom(), foundChamp.getNom());
//    }
//}
