package net.yc.race.track.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.yc.race.track.Enum.Status;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "season")
@Data
@NoArgsConstructor
public class Season {
    @Id
    private String seasonId;
    private Status status;
}
