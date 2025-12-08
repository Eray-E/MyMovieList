package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MovieService {

    @Autowired
    MovieRepository repo;

    public Movie save(Movie movie) {
        return repo.save(movie);
    }

    public MovieService(MovieRepository repo) {
        this.repo = repo;
    }

    public List<Movie> getAll() {
        return repo.findAll();
    }
}
