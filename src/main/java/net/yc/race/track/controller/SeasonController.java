package net.yc.race.track.controller;

import net.yc.race.track.model.Season;
import net.yc.race.track.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seasons")
public class SeasonController {

    @Autowired
    private SeasonService seasonService;

    @PostMapping("/add")
    public Season createSeason(@RequestBody Season season) {
        return seasonService.saveSeason(season);
    }

    @GetMapping
    public List<Season> findSeasons(){return seasonService.findSeasons();}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSeason(@PathVariable String id) {
        String result = seasonService.deleteSeasonById(id);
        if ("Season supprimé avec succès.".equals(result)) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(404).body(result);
        }
    }
}
