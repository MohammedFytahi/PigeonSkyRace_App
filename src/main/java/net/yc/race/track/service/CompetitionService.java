package net.yc.race.track.service;

import net.yc.race.track.model.Competition;
import net.yc.race.track.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CompetitionService {

    @Autowired
    private CompetitionRepository competitionRepository;

    public Competition saveCompetition(Competition competition) {
        return competitionRepository.save(competition);
    }

    public List<Competition> findCompetitions(){
        return competitionRepository.findAll();
    }

    public String deleteCompetitionById(Integer id) {
        if (competitionRepository.existsById(id)) {
            competitionRepository.deleteById(id);
            return "Competition supprimé avec succès.";
        } else {
            return "Competition non trouvé.";
        }
    }
}
