package net.yc.race.track.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "competitions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Competition {
    @Id
    private String id;
    private String seasonId;
    private String courseName;
    private String coordinatesGPS;
    private Long distance;
    private Date startDateTime;
    private Date delayDuration;
    private List<Integer> pigeonId;
}