package com.twu.biblioteca.catalogElement.movie;

import com.twu.biblioteca.catalogElement.CatalogElementsManager;

import java.util.List;

public class MoviesManager extends CatalogElementsManager {

    public MoviesManager(List<Movie> movies) {
        super(movies);
    }

    public void checkout(String reference) {
        int index = Integer.valueOf(reference) - 1;
        Movie movie = (Movie)catalogElements.get(index);
        movie.checkout();
    }
}
