package sukhushin.projects.url.shortener.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import sukhushin.projects.url.shortener.dto.OriginalUrl;
import sukhushin.projects.url.shortener.dto.ShortenedUrl;
import sukhushin.projects.url.shortener.entity.Url;
import sukhushin.projects.url.shortener.repository.UrlRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class UrlServiceImpl implements UrlService {
    @Autowired
    private UrlRepository urlRepository;
    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public ShortenedUrl shorten(OriginalUrl originalUrl) {
        Optional<Url> optionalUrl = urlRepository.findByOriginalUrl(originalUrl.originalUrl());

        if (optionalUrl.isEmpty()) {
            Url url = new Url(originalUrl.originalUrl());
            return new ShortenedUrl(urlRepository.save(url).getShortenedId().toString());
        }

        return new ShortenedUrl(optionalUrl.get().getShortenedId().toString());
    }

    public OriginalUrl find(String shortenedId) {
        Optional<Url> url = urlRepository.findById(UUID.fromString(shortenedId));
        if (url.isEmpty()) {
            throw new EntityNotFoundException();
        } else {
            return new OriginalUrl(url.get().getOriginalUrl());
        }
    }
}
