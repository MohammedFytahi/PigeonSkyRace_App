package net.yc.race.track.repository;

import net.yc.race.track.model.Pigeon;
import net.yc.race.track.model.Season;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonRepository extends MongoRepository<Season, Integer> {
}
