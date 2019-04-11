package com.twu.biblioteca.catalogElement.movie;

import com.twu.biblioteca.catalogElement.CatalogElementsManager;
import com.twu.biblioteca.user.User;

import java.util.List;

public class MoviesManager extends CatalogElementsManager {

    //TODO: delete
    private List<Movie> movies;


    public MoviesManager(List<Movie> movies) {
        super(movies);
        this.movies = movies; //TODO: delete
    }

    public void checkout(String reference, User loggedUser) {
        int index = Integer.valueOf(reference) - 1;
        Movie movie = (Movie)catalogElements.get(index);
        movie.checkout();
    }
}
