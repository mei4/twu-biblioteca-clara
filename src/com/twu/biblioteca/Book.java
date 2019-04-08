package com.twu.biblioteca;

public class Book {

    private String title;
    private String author;
    private int yearPublished;
    private boolean isCheckout;

    public Book(String title, String author, int yearPublished) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    public String getDetails() {
        return title + " | " + author + " | " + yearPublished;
    }

    public void setCheckout(boolean isCheckout) {
        this.isCheckout = isCheckout;
    }

    public boolean isCheckout() {
        return isCheckout;
    }
}
