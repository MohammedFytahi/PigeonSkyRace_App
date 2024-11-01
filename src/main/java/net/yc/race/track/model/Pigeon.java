package net.yc.race.track.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pigeons")
@Data
@NoArgsConstructor
public class Pigeon {
    @Id
    private int id;
    private long numeroDeBadge;
    private int age;
    private String couleur;
    private Competition competition;
}
