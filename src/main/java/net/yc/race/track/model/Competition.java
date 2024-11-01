package net.yc.race.track.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "competition")
@Getter
@Setter
@NoArgsConstructor
public class Competition {
    @Id
    private String id;  // Utilisation de String pour les identifiants MongoDB
    private String seasonId;  // Changement de type pour correspondre à la saison
    private String courseName;
    private String coordinatesGPS;
    private double distance;  // Utiliser double pour les valeurs numériques
    private Date startDateTime;
    private Date delayDuration;  // Renommé pour clarifier la signification
}
