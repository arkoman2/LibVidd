package com.bestteam.libvidd;

/**
 * Created by Arkadi on 26/12/2017.
 */

public class Movie
{
    private String MovieId;
    private String movieName;
    private int MovieRating;

    public Movie() {
    }

    public Movie(String movieId, String movieName, int MovieRating) {
        MovieId = movieId;
        this.movieName = movieName;
        this.MovieRating = MovieRating;
    }

    public String getMovieId() {
        return MovieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public int getMovieRating() {
        return MovieRating;
    }
}
