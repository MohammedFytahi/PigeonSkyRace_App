package net.yc.race.track.service;

import net.yc.race.track.Enum.Status;
import net.yc.race.track.model.Competition;
import net.yc.race.track.model.Pigeon;
import net.yc.race.track.model.Season;
import net.yc.race.track.repository.CompetitionRepository;
import net.yc.race.track.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public String updatePigeonToCompetition(Competition competition, Pigeon pigeon) {

        Date now = new Date();

        if (competition.getStartDateTime().compareTo(now) <= 0) {
            return "La competition et deja commencée ou completé . Impossible d'enregistrer le pigeon.";
        } else {

            competition.setPigeon(pigeon);
            competitionRepository.save(competition);
            return "Pigeon enregistrée avec succès.";
        }
    }


    public List<Competition> findCompetitions(){
        return competitionRepository.findAll();
    }

    public Optional<Competition> findCompetitionById(String id){
        return competitionRepository.findById(id);
    }

    public String deleteCompetitionById(String id) {
        if (competitionRepository.existsById(id)) {
            competitionRepository.deleteById(id);
            return "Competition supprimé avec succès.";
        } else {
            return "Competition non trouvé.";
        }
    }
}
