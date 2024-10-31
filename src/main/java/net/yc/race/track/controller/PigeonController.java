package net.yc.race.track.controller;

import net.yc.race.track.model.Pigeon;
import net.yc.race.track.service.PigeonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pigeons")
public class PigeonController {

    @Autowired
    private PigeonService pigeonService;

    @PostMapping("/add")
    public Pigeon createPigeon(@RequestBody Pigeon pigeon) {
        return pigeonService.savePigeon(pigeon);
    }

    @GetMapping
    public List<Pigeon> findPigeons(){return pigeonService.findPigeons();}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePigeon(@PathVariable Integer id) {
        String result = pigeonService.deletePigeonById(id);
        if ("Pigeon supprimé avec succès.".equals(result)) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(404).body(result);
        }
    }
}
