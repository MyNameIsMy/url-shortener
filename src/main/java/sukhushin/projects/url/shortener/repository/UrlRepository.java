package sukhushin.projects.url.shortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sukhushin.projects.url.shortener.entity.Url;

import java.util.Optional;
import java.util.UUID;

public interface UrlRepository extends JpaRepository<Url, UUID> {

    Optional<Url> findByOriginalUrl(String originalUrl);
}
