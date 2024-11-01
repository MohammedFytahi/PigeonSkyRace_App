package net.yc.race.track.controller;

import net.yc.race.track.model.Competition;
import net.yc.race.track.model.Pigeon;
import net.yc.race.track.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/competition")
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;

    @PostMapping("/add")
    public ResponseEntity<String> createCompetition(@RequestBody Competition competition) {
        String seasonId = competition.getSeasonId();
        String result = competitionService.saveCompetition(competition, seasonId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/addPigeonToCompetition/{competitionId}")
    public ResponseEntity<String> updatePigeonToCompetition(
            @PathVariable String competitionId, @RequestBody Pigeon pigeon) {

        Optional<Competition> competitionOpt = competitionService.findCompetitionById(competitionId);
        if (competitionOpt.isPresent()) {
            String result = competitionService.updatePigeonToCompetition(competitionOpt.get(), pigeon);
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(404).body("Competition not found.");
        }
    }

    @GetMapping
    public List<Competition> findCompetitions(){return competitionService.findCompetitions();}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCompetition(@PathVariable String id) {
        String result = competitionService.deleteCompetitionById(id);
        if ("Competition supprimé avec succès.".equals(result)) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(404).body(result);
        }
    }
}
