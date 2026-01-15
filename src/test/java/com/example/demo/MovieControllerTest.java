package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovieController.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MovieService movieService;

    @Test
    void getMovies() {
    }

    //Controller erstellt einen neuen Film, POST-Endpoint /movies wird getestet, Soll beweisen, dass mein Create Endpoint funktioniert//
    @Test
    void shouldCreateMovie() throws Exception {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("The Iron Claw");

        when(movieService.save(any(Movie.class))).thenReturn(movie); // egal welcher Film kommt, Service gibt diesen Movie zurück

        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                      "title": "The Iron Claw",
                      "genre": "Sport/Drama",
                      "releaseYear": 2023,
                      "status": "COMPLETED",
                      "rating": 8,
                      "favorite": false
                    }
                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("The Iron Claw"));
    }

    //Controller gibt alle Filme zurück, GET-Endpoint /movies wird getestet //
    @Test
    void shouldReturnAllMovies() throws Exception {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Avengers: Infinity War");

        when(movieService.getAll()).thenReturn(List.of(movie)); // gibt eine Liste mit einem Film zurück

        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value("Avengers: Infinity War"));
    }

    //Controller crasht nicht, wenn keine Filme vorhanden sind und antwortet trotzdem mit 200 OK //
    @Test
    void shouldReturnEmptyList_whenNoMoviesExist() throws Exception {
        when(movieService.getAll()).thenReturn(List.of()); // Service liefert eine leere Liste

        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(0));
    }

    //Controller gibt 500 internal Server Error zurück, wenn der Service eine Ausnahme wirft //
    @Test
    void shouldReturn500_whenServiceThrowsException() throws Exception {
        when(movieService.getAll())
                .thenThrow(new RuntimeException("DB error"));

        mockMvc.perform(get("/movies"))
                .andExpect(status().isInternalServerError());
    }
}
