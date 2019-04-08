package com.twu.biblioteca;

public class Movie extends CatalogElement {

    private int year;
    private String director;
    private int rating; //0: unrated range: from 1-10

    public Movie(String title, int year, String director, int rating) {
        this.title = title;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    @Override
    public String getDetails() {
        String details = title + " | " + year + " | " + director + " | ";
        details += (rating == 0) ? "unrated" : rating;
        return details;
    }
}
