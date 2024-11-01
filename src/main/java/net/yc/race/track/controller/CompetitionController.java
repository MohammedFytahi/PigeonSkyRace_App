package net.yc.race.track.controller;

import net.yc.race.track.model.Competition;
import net.yc.race.track.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
