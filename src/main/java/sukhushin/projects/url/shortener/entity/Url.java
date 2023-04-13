package sukhushin.projects.url.shortener.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name="urls")
@Getter
@Setter
@NoArgsConstructor
public class Url {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID shortenedId;
    @Column(name = "original_url", unique = true, nullable = false)
    private String originalUrl;

    public Url(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}
