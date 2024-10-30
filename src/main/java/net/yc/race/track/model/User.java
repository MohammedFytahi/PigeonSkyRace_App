package net.yc.race.track.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Getter @Setter @NoArgsConstructor
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String loftName;
    private String gpsCoordinates;

    public User(String id, String username, String password, String loftName, String gpsCoordinates) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.loftName = loftName;
        this.gpsCoordinates = gpsCoordinates;
    }
}