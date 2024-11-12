package net.yc.race.track.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "results")
@Data
@NoArgsConstructor
public class Result {
    @Id
    private int id;
    private int rank;
    private String loftName;
    private String numeroDeBadge;
    private Date arriveHour;
    private double distance;
    private double speed;
    private double point;
    private String competitionId;

}
