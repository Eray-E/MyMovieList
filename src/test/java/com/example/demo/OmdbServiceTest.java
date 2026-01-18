package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentCaptor;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


class OmdbServiceTest {

    private OmdbService omdbService;
    private RestTemplate restTemplate;

    @Test
    void search() {
    }

    @BeforeEach
    void setUp() {
        omdbService = new OmdbService();
        restTemplate = mock(RestTemplate.class);

        // private Felder setzen
        ReflectionTestUtils.setField(omdbService, "apiKey", "TEST_KEY");
        ReflectionTestUtils.setField(omdbService, "baseUrl", "http://test-omdb.com/");
        ReflectionTestUtils.setField(omdbService, "restTemplate", restTemplate);
    }

    @Test
    void search_buildsCorrectUrl_andReturnsResponse() {
        when(restTemplate.getForObject(anyString(), eq(String.class)))
                .thenReturn("search-result");

        String result = omdbService.search("batman");

        ArgumentCaptor<String> urlCaptor = ArgumentCaptor.forClass(String.class);
        verify(restTemplate).getForObject(urlCaptor.capture(), eq(String.class));

        assertThat(urlCaptor.getValue())
                .isEqualTo("http://test-omdb.com/?apikey=TEST_KEY&s=batman");

        assertThat(result).isEqualTo("search-result");
    }

    @Test
    void detail_buildsCorrectUrl_andReturnsResponse() {
        when(restTemplate.getForObject(anyString(), eq(String.class)))
                .thenReturn("detail-result");

        String result = omdbService.detail("tt123");

        ArgumentCaptor<String> urlCaptor = ArgumentCaptor.forClass(String.class);
        verify(restTemplate).getForObject(urlCaptor.capture(), eq(String.class));

        assertThat(urlCaptor.getValue())
                .isEqualTo("http://test-omdb.com/?apikey=TEST_KEY&i=tt123");

        assertThat(result).isEqualTo("detail-result");
    }
}