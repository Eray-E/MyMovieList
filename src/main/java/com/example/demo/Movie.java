package com.example.demo;

public class Movie {
    String title;
    String genre;
    int releaseYear;
    boolean watched;


    public Movie(String title,  String genre, int releaseYear, boolean watched) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.watched = watched;
    }

    public boolean isWatched() {
        return watched;
    }
    public void setWatched(boolean watched) {
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
}


