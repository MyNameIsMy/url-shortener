package sukhushin.projects.url.shortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import sukhushin.projects.url.shortener.dto.OriginalUrl;
import sukhushin.projects.url.shortener.dto.ShortenedUrl;
import sukhushin.projects.url.shortener.dto.UrlStatistic;
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

    @GetMapping("/find/{shortened-id}")
    public RedirectView find(@PathVariable("shortened-id") String shortenedId) {
        OriginalUrl originalUrl = urlService.find(shortenedId);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(originalUrl.originalUrl());
        return redirectView;
    }

    @GetMapping("/statistic/{shortened-id}")
    public ResponseEntity<UrlStatistic> statistic(@PathVariable("shortened-id") String shortenedId) {
        UrlStatistic urlStatistic = urlService.statistic(shortenedId);

        return ResponseEntity.ok(urlStatistic);
    }
}
