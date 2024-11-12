package net.yc.race.track.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import net.yc.race.track.model.Competition;
import net.yc.race.track.model.Result;
import net.yc.race.track.model.User;
import net.yc.race.track.repository.CompetitionRepository;
import net.yc.race.track.repository.ResultRepository;
import net.yc.race.track.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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


    public List<Result> showResult(String competitionId) {
        // Fetch all results, sorted by speed in descending order
        List<Result> results = resultRepository.findAllByCompetitionId(
                competitionId, Sort.by(Sort.Order.desc("speed"))
        );

        // Calculate the number of players to return (top 25%)
        int totalPlayers = results.size();
        int top25PercentCount = (int) Math.ceil(totalPlayers * 0.25); // Round up to ensure at least one player

        // Get the top 25% results
        List<Result> topResults = results.subList(0, Math.min(top25PercentCount, totalPlayers));

        // Set initial points for the top rank and calculate points difference
        double topPoints = 100.0;
        double pointDifference = topPoints / (topResults.size() - 1.0); // Avoid zero difference if only one player

        for (int i = 0; i < topResults.size(); i++) {
            // Assign rank
            Result result = topResults.get(i);
            int rank = i + 1;
            result.setRank(rank);

            // Calculate points based on rank
            double points = topPoints - (rank - 1) * pointDifference;
            result.setPoint(Math.max(points, 0)); // Ensure points don't go negative
        }

        return topResults;
    }



    public String saveResult(Result result) {
        Optional<Competition> competitionOpt = competitionRepository.findById(result.getCompetitionId());
        Optional<User> userOpt = userRepository.findByLoftName(result.getLoftName());

        if (competitionOpt.isPresent() && userOpt.isPresent()) {
            Competition competition = competitionOpt.get();
            User user = userOpt.get();

            // Calculate competition end time by adding delay to start time
            Instant startTime = competition.getStartDateTime().toInstant();
            Instant delayDuration = competition.getDelayDuration().toInstant();
            Instant endTime = startTime.plus(delayDuration.toEpochMilli(), ChronoUnit.MILLIS);

            // Check if the current time is after the end time
            Instant now = Instant.now();
            if (now.isAfter(endTime)) {
                return "The competition delay duration has already passed. Result cannot be added.";
            }

            // Calculate distance using GPS coordinates
            double distance = calculateDistance(
                    competition.getCoordinatesGPS(),
                    user.getGpsCoordinates()
            );
             result.setDistance(distance);

            // Calculate elapsed time in minutes
            Date arriveHour = result.getArriveHour();
            long timeElapsedMinutes = Duration.between(startTime, arriveHour.toInstant()).toMinutes();

            // Calculate speed in m/min
            result.setSpeed(timeElapsedMinutes > 0 ? distance*1000 / timeElapsedMinutes : 0);

            // Save result
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
