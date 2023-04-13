package sukhushin.projects.url.shortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sukhushin.projects.url.shortener.dto.OriginalUrl;
import sukhushin.projects.url.shortener.dto.ShortenedUrl;
import sukhushin.projects.url.shortener.service.UrlService;

@RestController
@RequestMapping("/url")
public class UrlController {
    @Autowired
    private UrlService urlService;
    @PostMapping("/shorten")
    public ResponseEntity<ShortenedUrl> shorten(@RequestBody OriginalUrl originalUrl) {
        ShortenedUrl shortenedUrl = urlService.shorten(originalUrl);

        return ResponseEntity.ok(shortenedUrl);
    }

    @GetMapping("/find")
    public ResponseEntity<OriginalUrl> find(@RequestParam("shortened-id") String shortenedId) {
        OriginalUrl originalUrl = urlService.find(shortenedId);

        return ResponseEntity.ok(originalUrl);
    }
}
