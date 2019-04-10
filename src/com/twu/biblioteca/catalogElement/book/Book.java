package com.twu.biblioteca.catalogElement.book;

import com.twu.biblioteca.catalogElement.CatalogElement;

public class Book extends CatalogElement {

    private String author;
    private int yearPublished;

    public Book(String title, String author, int yearPublished) {
        super("Thank you! Enjoy the book",
                "Sorry, that book is not available",
                "Thank you for returning the book",
                "That is not a valid book to return");
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    //TODO: delete
    @Override
    public String getDetails() {
        return title + " | " + author + " | " + yearPublished;
    }

    public String getAuthor() {
        return author;
    }

    public int getYearPublished() {
        return yearPublished;
    }
}
