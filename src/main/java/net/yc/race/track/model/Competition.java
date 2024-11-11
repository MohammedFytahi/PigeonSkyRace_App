package net.yc.race.track.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "competition")
@Getter
@Setter
@NoArgsConstructor
public class Competition {
    @Id
    private String id;
    private String seasonId;
    private String courseName;
    private String coordinatesGPS;
    private double distance;
    private Date startDateTime;
    private Date delayDuration;
    private List<Pigeon> pigeon;
}
