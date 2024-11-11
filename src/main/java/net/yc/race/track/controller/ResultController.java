package net.yc.race.track.controller;

import net.yc.race.track.model.Result;
import net.yc.race.track.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/results")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @PostMapping("/add")
    public ResponseEntity<String> createResult(@RequestBody Result result) {
        String saveMessage = resultService.saveResult(result);

        if ("Result ajouté avec succès.".equals(saveMessage)) {
            return ResponseEntity.ok(saveMessage);
        } else {
            return ResponseEntity.status(404).body(saveMessage);
        }
    }


    @GetMapping
    public List<Result> findResults(){return resultService.findResults();}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteResult(@PathVariable Integer id) {
        String result = resultService.deleteResultById(id);
        if ("Result supprimé avec succès.".equals(result)) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(404).body(result);
        }
    }

}
