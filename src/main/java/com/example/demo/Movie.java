package com.example.demo;

import jakarta.persistence.*;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String genre;
    private int releaseYear;


    @Column(nullable = false)
    private boolean favorite = false;

    @Enumerated(EnumType.STRING)
    private MovieStatus status;

    private Integer rating;

    public Movie() {}


    public Movie(String title,  String genre, int releaseYear, MovieStatus status, Integer rating) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.status = status;
        this.rating = rating;
    }

    public MovieStatus getStatus() {
        return status;
    }
    public void setStatus(MovieStatus status) {
        this.status = status;
    }
    public Integer getRating() {
        return rating;
    }
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public int getReleaseYear() {
        return releaseYear;
    }
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public boolean getFavorite() {
        return favorite;
    }
    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}


