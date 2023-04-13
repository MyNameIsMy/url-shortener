package sukhushin.projects.url.shortener.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ShortenedUrl(
        @JsonProperty(value = "shortened_url", required = true) String shortenedUrl,
        @JsonProperty(value = "url_statistic") UrlStatistic urlStatistic
) {
}
