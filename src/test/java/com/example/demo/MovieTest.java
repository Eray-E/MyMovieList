package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MovieTest {

    @Autowired
    private MovieService movieService;

    @MockitoBean
    private MovieRepository movieRepository;

    private Movie movie;

    @Test
    void getStatus() {
    }

}