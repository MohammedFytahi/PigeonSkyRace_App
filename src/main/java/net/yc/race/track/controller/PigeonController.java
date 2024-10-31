package net.yc.race.track.controller;

import net.yc.race.track.model.Pigeon;
import net.yc.race.track.service.PigeonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pigeons")
public class PigeonController {

    @Autowired
    private PigeonService pigeonService;

    @PostMapping
    public Pigeon createPigeon(@RequestBody Pigeon pigeon) {
        return pigeonService.savePigeon(pigeon);
    }
}
