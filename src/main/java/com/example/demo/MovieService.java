package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MovieService {

    @Autowired
    MovieRepository repo;

    public Movie save(Movie movie) {
        if(movie.getStatus() == null) {
            movie.setStatus(MovieStatus.PLANNED);
        }
        return repo.save(movie);
    }

    public Movie update(Long id, Movie updated) {
        Movie movie = repo.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
        movie.setTitle(updated.getTitle());
        movie.setGenre(updated.getGenre());
        movie.setReleaseYear(updated.getReleaseYear());
        movie.setStatus(updated.getStatus());
        movie.setRating(updated.getRating());
        movie.setFavorite(updated.getFavorite());
        return repo.save(movie);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public MovieService(MovieRepository repo) {
        this.repo = repo;
    }

    public List<Movie> getAll() {
        return repo.findAll();
    }
}
