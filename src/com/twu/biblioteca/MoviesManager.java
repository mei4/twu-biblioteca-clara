package com.twu.biblioteca;

import java.util.List;
import java.util.stream.IntStream;

public class MoviesManager {

    private List<Movie> movies;

    public MoviesManager(List<Movie> movies) {
        this.movies = movies;
    }

    public void showAllMovies() {
        IntStream.range(0, movies.size()).forEach(i -> System.out.println(i+1 + "- " + movies.get(i).getDetails()));
    }
}
