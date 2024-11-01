package net.yc.race.track.service;

import net.yc.race.track.Enum.Status;
import net.yc.race.track.model.Competition;
import net.yc.race.track.model.Season;
import net.yc.race.track.repository.CompetitionRepository;
import net.yc.race.track.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class CompetitionService {

    @Autowired
    private CompetitionRepository competitionRepository;
    @Autowired
    private SeasonRepository seasonRepository;

    public String saveCompetition(Competition competition, String seasonId) {
        Optional<Season> seasonOpt = seasonRepository.findById(seasonId);
        if (seasonOpt.isPresent() && seasonOpt.get().getStatus() == Status.DONE) {
            return "La saison n'est pas active. Impossible d'enregistrer la compétition.";
        } else {
            competitionRepository.save(competition);
            return "Compétition enregistrée avec succès.";
        }
    }


    public List<Competition> findCompetitions(){
        return competitionRepository.findAll();
    }

    public String deleteCompetitionById(String id) {
        if (competitionRepository.existsById(Integer.valueOf(id))) {
            competitionRepository.deleteById(Integer.valueOf(id));
            return "Competition supprimé avec succès.";
        } else {
            return "Competition non trouvé.";
        }
    }
}
