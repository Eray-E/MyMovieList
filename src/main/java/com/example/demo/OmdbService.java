package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OmdbService {

    @Value("${omdb.api.key}")
    private String apiKey;

    @Value("${OMDB_API_URL}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String search(String query) {
        String url = baseUrl +
                "?apikey=" + apiKey +
                "&s=" + query;

        return restTemplate.getForObject(url, String.class);
    }

    public String detail(String imdbId) {
        String url = baseUrl +
                "?apikey=" + apiKey +
                "&i=" + imdbId;

        return restTemplate.getForObject(url, String.class);
    }
}
