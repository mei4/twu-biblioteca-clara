package com.twu.biblioteca;

import com.sun.tools.javac.util.Names;

public class Book {

    private String title;
    private String author;
    private int yearPublished;
    private boolean isCheckedOut;

    public Book(String title, String author, int yearPublished) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    public String getDetails() {
        return title + " | " + author + " | " + yearPublished;
    }

    public void setCheckedOut() {
        isCheckedOut = true;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }
}
