package sukhushin.projects.url.shortener.service;

import sukhushin.projects.url.shortener.dto.OriginalUrl;
import sukhushin.projects.url.shortener.dto.ShortenedUrl;
import sukhushin.projects.url.shortener.dto.UrlStatistic;


public interface UrlService {

    ShortenedUrl shorten(OriginalUrl originalUrl);

    OriginalUrl find(String shortenedId);

    UrlStatistic statistic(String shortenedId);
}
