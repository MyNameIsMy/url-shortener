package sukhushin.projects.url.shortener.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import sukhushin.projects.url.shortener.dto.OriginalUrl;
import sukhushin.projects.url.shortener.dto.ShortenedUrl;
import sukhushin.projects.url.shortener.dto.UrlStatistic;
import sukhushin.projects.url.shortener.entity.Url;
import sukhushin.projects.url.shortener.repository.UrlRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class UrlServiceImpl implements UrlService {
    @Autowired
    private UrlRepository urlRepository;

    @Value("${short-url.base}")
    private String shortUrlBase;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public ShortenedUrl shorten(OriginalUrl originalUrl) {
        Optional<Url> optionalUrl = urlRepository.findByOriginalUrl(originalUrl.originalUrl());

        if (optionalUrl.isEmpty()) {
            Url url = new Url(originalUrl.originalUrl());
            return new ShortenedUrl(
                    shortUrlBase + urlRepository.save(url).getShortenedId().toString(),
                    new UrlStatistic(
                            url.getShorteningCount(),
                            url.getAccessingCount()
                    )
            );
        }

        Url url = optionalUrl.get();
        url.setShorteningCount(url.getShorteningCount() + 1);

        return new ShortenedUrl(
                shortUrlBase + url.getShortenedId().toString(),
                new UrlStatistic(
                        optionalUrl.get().getShorteningCount(),
                        optionalUrl.get().getAccessingCount()
                )
        );
    }
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public OriginalUrl find(String shortenedId) {
        Optional<Url> optionalUrl = urlRepository.findById(UUID.fromString(shortenedId));
        if (optionalUrl.isEmpty()) {
            throw new EntityNotFoundException();
        } else {
            Url url = optionalUrl.get();
            url.setAccessingCount(url.getAccessingCount() + 1);
            return new OriginalUrl(url.getOriginalUrl());
        }
    }

    @Override
    public UrlStatistic statistic(String shortenedId) {
        Optional<Url> optionalUrl = urlRepository.findById(UUID.fromString(shortenedId));
        if (optionalUrl.isEmpty()) {
            throw new EntityNotFoundException();
        } else {
            Url url = optionalUrl.get();
            return new UrlStatistic(url.getShorteningCount(), url.getAccessingCount());
        }
    }
}
