package com.example.demo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(OmdbController.class)
class OmdbControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OmdbService omdbService;

    @Test
    void searchEndpoint_returnsSearchResult() throws Exception {
        Mockito.when(omdbService.search("batman"))
                .thenReturn("search-result");

        mockMvc.perform(get("/api/omdb/search")
                        .param("query", "batman"))
                .andExpect(status().isOk())
                .andExpect(content().string("search-result"));
    }

    @Test
    void detailEndpoint_returnsDetailResult() throws Exception {
        Mockito.when(omdbService.detail("tt123"))
                .thenReturn("detail-result");

        mockMvc.perform(get("/api/omdb/detail")
                        .param("imdbId", "tt123"))
                .andExpect(status().isOk())
                .andExpect(content().string("detail-result"));
    }
}
