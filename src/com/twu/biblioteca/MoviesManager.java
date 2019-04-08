package com.twu.biblioteca;

import java.util.List;

public class MoviesManager<T extends CatalogElement> extends CatalogElementsManager<T> {

    private List<Movie> movies;

    public MoviesManager(List<Movie> movies) {
        super((List<T>) movies, "", "", "", "");
        this.movies = movies;
    }

//    public void showAll() {
//        IntStream.range(0, movies.size()).forEach(i -> System.out.println(i+1 + "- " + movies.get(i).getDetails()));
//    }
//
//    public void checkout(String movieReference) {
//        int index = Integer.valueOf(movieReference) - 1;
//        Movie movie = movies.get(index);
//        movie.setCheckout(true);
//    }
}
