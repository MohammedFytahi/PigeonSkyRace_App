package net.yc.race.track.service;

import net.yc.race.track.model.Pigeon;
import net.yc.race.track.model.User;
import net.yc.race.track.repository.PigeonRepository;
import net.yc.race.track.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class PigeonService {

    @Autowired
    private PigeonRepository pigeonRepository;
    @Autowired
    private UserRepository userRepository;

    public String savePigeon(Pigeon pigeon, String user_id) {
        Optional<User> userOpt = userRepository.findById(user_id);

        if(userOpt.isPresent()){
           pigeonRepository.save(pigeon);
           return "pigeon enregistrée avec succès.";
        }else {
            return "User not found";
        }

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
