package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;

    @Mock
    private MovieRepository repo;

    private Movie movie;

    @BeforeEach
    void setUp() {
        movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Inception");
        movie.setGenre("Action");
        movie.setReleaseYear(2010);
        movie.setStatus(MovieStatus.COMPLETED);
        movie.setRating(9);
        movie.setFavorite(false);
    }

    @Test
    void save() {
    }

    @Test
    void shouldReturnAllMovies() {
        when(repo.findAll()).thenReturn(List.of(movie));

        List<Movie> result = movieService.getAll();

        assertEquals(1, result.size());
        assertEquals("Inception", result.get(0).getTitle());
    }

    @Test
    void update_shouldUpdateFields() {
        when(repo.findById(1L)).thenReturn(Optional.of(movie));
        when(repo.save(any(Movie.class))).thenReturn(movie);

        Movie updated = new Movie();
        updated.setTitle("Chainsaw Man - The Movie: Reze Arc");
        updated.setGenre("Anime");
        updated.setReleaseYear(2025);
        updated.setStatus(MovieStatus.COMPLETED);
        updated.setRating(10);
        updated.setFavorite(true);

        Movie result = movieService.update(1L, updated);

        assertEquals("Chainsaw Man - The Movie: Reze Arc", result.getTitle());
        assertTrue(result.getFavorite());
    }

    @Test
    void delete_shouldCallRepository() {
        movieService.delete(1L);

        verify(repo).deleteById(1L);
    }

    @Test
    void save_DefaultStatus() {
        movie.setStatus(null);

        when(repo.save(any(Movie.class))).thenReturn(movie);

        Movie saved = movieService.save(movie);

        assertEquals(MovieStatus.PLANNED, saved.getStatus());
    }

    @Test
    void save_NoNegativeYears() {
        movie.setReleaseYear(-2020);

        assertThrows(IllegalArgumentException.class, () -> {
            movieService.save(movie);
        });
    }

    @Test
    void update_shouldThrowException_whenMovieNotFound() {
        when(repo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () ->
                movieService.update(1L, movie)
        );
    }


}