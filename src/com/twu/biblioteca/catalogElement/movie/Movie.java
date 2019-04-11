package com.twu.biblioteca.catalogElement.movie;

import com.twu.biblioteca.catalogElement.CatalogElement;

public class Movie extends CatalogElement {

    private int year;
    private String director;
    private int rating; //0: unrated range: from 1-10

    public Movie(String title, int year, String director, int rating) {
        super();
        this.title = title;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    public void checkout() {
        isCheckout = true;
    }

    public int getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public int getRating() {
        return rating;
    }
}
