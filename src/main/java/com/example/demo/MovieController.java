package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class MovieController {

    @GetMapping("/todos")
    public List<Movie> getMovies(){
        return List.of(new Movie("M1","M2",2025,true));
    }
}
