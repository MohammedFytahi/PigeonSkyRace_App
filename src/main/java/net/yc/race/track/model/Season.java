package net.yc.race.track.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.yc.race.track.Enum.Status;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "saison")
@Getter @Setter
@NoArgsConstructor
public class Season {
    @Id
    private int id;
    private Status status;
}
