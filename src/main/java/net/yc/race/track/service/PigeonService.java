package net.yc.race.track.service;

import net.yc.race.track.model.Pigeon;
import net.yc.race.track.repository.PigeonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PigeonService {

    @Autowired
    private PigeonRepository pigeonRepository;

    public Pigeon savePigeon(Pigeon pigeon) {
        return pigeonRepository.save(pigeon);
    }
}
