package com.example.demo;


import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins= "*")
@RestController
@RequestMapping("/api/omdb")
public class OmdbController {

    private final OmdbService omdbService;

    public OmdbController(OmdbService omdbService) {
        this.omdbService = omdbService;
    }

    @GetMapping("/search")
    public String search(@RequestParam String query) {
        return omdbService.search(query);
    }

    @GetMapping("/detail")
    public String detail(@RequestParam String imdbId) {
        return omdbService.detail(imdbId);
    }
}

