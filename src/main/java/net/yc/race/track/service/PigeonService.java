package net.yc.race.track.service;

import net.yc.race.track.model.Pigeon;
import net.yc.race.track.repository.PigeonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class PigeonService {

    @Autowired
    private PigeonRepository pigeonRepository;

    public Pigeon savePigeon(Pigeon pigeon) {
        return pigeonRepository.save(pigeon);
    }

    public List<Pigeon> findPigeons(){
        return pigeonRepository.findAll();
    }

    public String deletePigeonById(Integer id) {
        if (pigeonRepository.existsById(id)) {
            pigeonRepository.deleteById(id);
            return "Pigeon supprimé avec succès.";
        } else {
            return "Pigeon non trouvé.";
        }
    }
}
