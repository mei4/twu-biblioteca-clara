package com.twu.biblioteca;

public class Book extends CatalogElement {

    private String author;
    private int yearPublished;

    public Book(String title, String author, int yearPublished) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    @Override
    public String getDetails() {
        return title + " | " + author + " | " + yearPublished;
    }

}
