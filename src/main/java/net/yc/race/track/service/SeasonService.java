package net.yc.race.track.service;

import net.yc.race.track.model.Season;
import net.yc.race.track.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class SeasonService {

    @Autowired
    private SeasonRepository seasonRepository;

    public Season saveSeason(Season season) {
        return seasonRepository.save(season);
    }

    public List<Season> findSeasons(){
        return seasonRepository.findAll();
    }

    public String deleteSeasonById(Integer id) {
        if (seasonRepository.existsById(id)) {
            seasonRepository.deleteById(id);
            return "Season supprimé avec succès.";
        } else {
            return "Season non trouvé.";
        }
    }
}
