package net.yc.race.track.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pigeons")
@Getter
@Setter
@NoArgsConstructor

public class Pigeon {
    @Id
    private int id;
    private String user_id;
    private long numeroDeBadge;
    private int age;
    private String couleur;
    private Competition competition;
}
