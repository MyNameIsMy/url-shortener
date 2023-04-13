package sukhushin.projects.url.shortener.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UrlStatistic(
        @JsonProperty(value = "shortening_count", required = true) Long shorteningCount,
        @JsonProperty(value = "accessing_count", required = true) Long accessingCount
) {
}
