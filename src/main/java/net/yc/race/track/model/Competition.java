package net.yc.race.track.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Competition")
@Getter @Setter
@NoArgsConstructor
public class Competition {
    @Id
    private int id;
    private String courseName;
    private String coordinatesGPS;
    private String distance;
    private Date dateAndHour;
    private Date Delay;

}
