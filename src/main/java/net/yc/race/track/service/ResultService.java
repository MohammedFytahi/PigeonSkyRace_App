package net.yc.race.track.service;


import net.yc.race.track.model.Competition;
import net.yc.race.track.model.Result;
import net.yc.race.track.model.User;
import net.yc.race.track.repository.CompetitionRepository;
import net.yc.race.track.repository.ResultRepository;
import net.yc.race.track.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ResultService {

        @Autowired
        private ResultRepository resultRepository;

        @Autowired
        private CompetitionRepository competitionRepository;

        @Autowired
        private UserRepository userRepository;

    public String saveResult(Result result) {
        Optional<Competition> competitionOpt = competitionRepository.findById(result.getCompetitionId());
        Optional<User> userOpt = userRepository.findByLoftName(result.getLoftName());

        if (competitionOpt.isPresent() && userOpt.isPresent()) {
            Competition competition = competitionOpt.get();
            User user = userOpt.get();

            double distance = calculateDistance(
                    competition.getCoordinatesGPS(),
                    user.getGpsCoordinates()
            );

            result.setDistance(distance);

            Date arriveHour = result.getArriveHour();
            Date startDateTime = competition.getStartDateTime();
            long timeElapsed = Duration.between(startDateTime.toInstant(), arriveHour.toInstant()).toHours();

            if (timeElapsed > 0) {
                double speed = distance / timeElapsed;
                result.setSpeed(speed);
            } else {
                result.setSpeed(0);
            }


            resultRepository.save(result);
            return "Result enregistré avec succès.";
        } else {
            return "Competition or User not found.";
        }
    }

    private double calculateDistance(String gps1, String gps2) {
        String[] coordinates1 = gps1.split(",");
        String[] coordinates2 = gps2.split(",");

        double lat1 = Math.toRadians(Double.parseDouble(coordinates1[0]));
        double lon1 = Math.toRadians(Double.parseDouble(coordinates1[1]));
        double lat2 = Math.toRadians(Double.parseDouble(coordinates2[0]));
        double lon2 = Math.toRadians(Double.parseDouble(coordinates2[1]));

        final int EARTH_RADIUS = 6371;

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }

        public List<Result> findResults(){
            return resultRepository.findAll();
        }

        public String deleteResultById(Integer id) {
            if (resultRepository.existsById(id)) {
                resultRepository.deleteById(id);
                return "Result supprimé avec succès.";
            } else {
                return "Result non trouvé.";
            }
        }

}
