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

        if (userOpt.isPresent()) {
            String generatedBadge = generateUniqueBadge(pigeon);

            // Check if a pigeon with this badge number already exists
            while (pigeonRepository.existsByNumeroDeBadge(generatedBadge)) {
                // Modify the generated badge slightly to ensure uniqueness if a duplicate is found
                generatedBadge = generateUniqueBadge(pigeon);
            }

            pigeon.setNumeroDeBadge(generatedBadge);
            pigeon.setUser_id(user_id);
            pigeonRepository.save(pigeon);
            return "Pigeon enregistré avec succès avec le numéro de bague: " + generatedBadge;
        } else {
            return "Utilisateur non trouvé";
        }
    }

    public Optional<Pigeon> findPigeonById(Integer pigeonId){
        return pigeonRepository.findById(pigeonId);
    }

    // Helper method to generate a unique badge based on color and age
    private String generateUniqueBadge(Pigeon pigeon) {
        // Combine color, age, and a random number or timestamp to ensure uniqueness
        return pigeon.getCouleur().substring(0, 2).toUpperCase() // Color prefix (first 2 letters)
                + pigeon.getAge() // Age as part of badge
                + System.currentTimeMillis() % 1000; // Last 3 digits of timestamp for uniqueness
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
