package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class MovieController {

    @Autowired
    MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @CrossOrigin
    @GetMapping("/movies")
    public List<Movie> getMovies(){
        return service.getAll();
    }

    @PostMapping("/movies")
    public Movie createMovie(@RequestBody Movie movie) {
        return service.save(movie);
    }
}
