package net.yc.race.track.service;

import net.yc.race.track.model.Pigeon;
import net.yc.race.track.model.User;
import net.yc.race.track.repository.PigeonRepository;
import net.yc.race.track.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PigeonServiceTest {

    @Mock
    private PigeonRepository pigeonRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PigeonService pigeonService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void savePigeon_UserExists_ShouldSavePigeon() {

        String userId = "1";
        Pigeon pigeon = new Pigeon();
        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(pigeonRepository.save(pigeon)).thenReturn(pigeon);


        String result = pigeonService.savePigeon(pigeon, userId);


        assertEquals("pigeon enregistrée avec succès.", result);
        verify(userRepository, times(1)).findById(userId);
        verify(pigeonRepository, times(1)).save(pigeon);
    }

    @Test
    void savePigeon_UserDoesNotExist_ShouldReturnUserNotFound() {

        String userId = "2";
        Pigeon pigeon = new Pigeon();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());


        String result = pigeonService.savePigeon(pigeon, userId);

        assertEquals("User not found", result);
        verify(userRepository, times(1)).findById(userId);
        verify(pigeonRepository, never()).save(pigeon);
    }



    @Test
    void findPigeons_ShouldReturnAllPigeons() {

        Pigeon pigeon1 = new Pigeon();
        pigeon1.setId(1);
        pigeon1.setCouleur("Bleu");

        Pigeon pigeon2 = new Pigeon();
        pigeon2.setId(2);
        pigeon2.setCouleur("Rouge");

        List<Pigeon> pigeons = Arrays.asList(pigeon1, pigeon2);
        when(pigeonRepository.findAll()).thenReturn(pigeons);


        List<Pigeon> result = pigeonService.findPigeons();


        assertEquals(2, result.size());
        assertEquals(pigeons, result);
    }

    @Test
    void deletePigeonById_ShouldReturnSuccessMessage_WhenPigeonExists() {
        // Arrange
        Integer pigeonId = 1;
        when(pigeonRepository.existsById(pigeonId)).thenReturn(true);

        // Act
        String result = pigeonService.deletePigeonById(pigeonId);

        // Assert
        assertEquals("Pigeon supprimé avec succès.", result);
        verify(pigeonRepository, times(1)).deleteById(pigeonId);
    }

    @Test
    void deletePigeonById_ShouldReturnNotFoundMessage_WhenPigeonDoesNotExist() {
        // Arrange
        Integer pigeonId = 2;
        when(pigeonRepository.existsById(pigeonId)).thenReturn(false);

        // Act
        String result = pigeonService.deletePigeonById(pigeonId);

        // Assert
        assertEquals("Pigeon non trouvé.", result);
        verify(pigeonRepository, never()).deleteById(pigeonId);
    }
}