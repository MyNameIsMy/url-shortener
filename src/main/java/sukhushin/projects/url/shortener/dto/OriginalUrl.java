package sukhushin.projects.url.shortener.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OriginalUrl(@JsonProperty(value = "original_url", required = true) String originalUrl) {
}
