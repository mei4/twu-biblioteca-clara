package com.twu.biblioteca.catalogElement.movie;

import com.twu.biblioteca.catalogElement.CatalogElement;
import com.twu.biblioteca.catalogElement.CatalogElementsManager;

import java.util.List;

public class MoviesManager<T extends CatalogElement> extends CatalogElementsManager<T> {

    //TODO: delete
    private List<Movie> movies;

    public MoviesManager(List<Movie> movies) {
        super((List<T>) movies);
        this.movies = movies; //TODO: delete
    }
}
