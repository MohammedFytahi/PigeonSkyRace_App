package net.yc.race.track.service;

import net.yc.race.track.Enum.Status;
import net.yc.race.track.model.Competition;
import net.yc.race.track.model.Season;
import net.yc.race.track.repository.CompetitionRepository;
import net.yc.race.track.repository.SeasonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CompetitionServiceTest {

    @Mock
    private CompetitionRepository competitionRepository;

    @Mock
    private SeasonRepository seasonRepository;

    @InjectMocks
    private CompetitionService competitionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCompetition_ShouldReturnErrorMessage_WhenSeasonIsDone() {

        String seasonId = "1";
        Season doneSeason = new Season();
        doneSeason.setStatus(Status.DONE);

        Competition competition = new Competition();
        when(seasonRepository.findById(seasonId)).thenReturn(Optional.of(doneSeason));


        String result = competitionService.saveCompetition(competition, seasonId);


        assertEquals("La saison n'est pas active. Impossible d'enregistrer la compétition.", result);
        verify(competitionRepository, never()).save(any(Competition.class));
    }

    @Test
    void saveCompetition_ShouldSaveCompetition_WhenSeasonIsActiveOrNotFound() {

        String seasonId = "2";
        Season activeSeason = new Season();
        activeSeason.setStatus(Status.NOT_YET);

        Competition competition = new Competition();
        when(seasonRepository.findById(seasonId)).thenReturn(Optional.of(activeSeason));


        String result = competitionService.saveCompetition(competition, seasonId);


        assertEquals("Compétition enregistrée avec succès.", result);
        verify(competitionRepository, times(1)).save(competition);
    }

    @Test
    void saveCompetition_ShouldSaveCompetition_WhenSeasonIsNotFound() {

        String seasonId = "3";
        Competition competition = new Competition();
        when(seasonRepository.findById(seasonId)).thenReturn(Optional.empty());


        String result = competitionService.saveCompetition(competition, seasonId);


        assertEquals("Compétition enregistrée avec succès.", result);
        verify(competitionRepository, times(1)).save(competition);
    }

    @Test
    void findCompetitions_ShouldReturnListOfCompetitions() {

        Competition competition1 = new Competition();
        competition1.setId("1");
        competition1.setCourseName("Course 1");

        Competition competition2 = new Competition();
        competition2.setId("2");
        competition2.setCourseName("Course 2");

        List<Competition> expectedCompetitions = Arrays.asList(competition1, competition2);

        when(competitionRepository.findAll()).thenReturn(expectedCompetitions);


        List<Competition> actualCompetitions = competitionService.findCompetitions();


        assertEquals(expectedCompetitions.size(), actualCompetitions.size());
        assertEquals(expectedCompetitions, actualCompetitions);
    }

    @Test
    void findCompetitionById_ShouldReturnCompetition_WhenIdExists() {

        String competitionId = "1";
        Competition competition = new Competition();
        competition.setId(competitionId);
        competition.setCourseName("Course Test");

        when(competitionRepository.findById(competitionId)).thenReturn(Optional.of(competition));


        Optional<Competition> result = competitionService.findCompetitionById(competitionId);


        assertTrue(result.isPresent());
        assertEquals(competition, result.get());
    }

    @Test
    void findCompetitionById_ShouldReturnEmptyOptional_WhenIdDoesNotExist() {

        String competitionId = "nonExistentId";
        when(competitionRepository.findById(competitionId)).thenReturn(Optional.empty());


        Optional<Competition> result = competitionService.findCompetitionById(competitionId);


        assertTrue(result.isEmpty());
    }


    @Test
    void deleteCompetitionById_ShouldReturnSuccessMessage_WhenCompetitionExists() {

        String competitionId = "1";
        when(competitionRepository.existsById(competitionId)).thenReturn(true);


        String result = competitionService.deleteCompetitionById(competitionId);


        assertEquals("Competition supprimé avec succès.", result);
        verify(competitionRepository, times(1)).deleteById(competitionId);
    }

    @Test
    void deleteCompetitionById_ShouldReturnNotFoundMessage_WhenCompetitionDoesNotExist() {

        String competitionId = "nonExistentId";
        when(competitionRepository.existsById(competitionId)).thenReturn(false);


        String result = competitionService.deleteCompetitionById(competitionId);


        assertEquals("Competition non trouvé.", result);
        verify(competitionRepository, never()).deleteById(competitionId);
    }

}
