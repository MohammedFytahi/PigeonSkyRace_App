package net.yc.race.track.repository;

import net.yc.race.track.model.Pigeon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PigeonRepository extends MongoRepository<Pigeon, Integer> {
}
