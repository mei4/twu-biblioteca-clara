package com.twu.biblioteca.catalogElement.book;

import com.twu.biblioteca.catalogElement.CatalogElement;
import com.twu.biblioteca.catalogElement.exceptions.CannotCheckOutCatalogElement;
import com.twu.biblioteca.catalogElement.exceptions.CannotReturnCatalogElement;

public class Book extends CatalogElement {

    private String author;
    private int yearPublished;
    private String userWhoCheckedOut;

    private String successMessageCheckout = "Thank you! Enjoy the book";
    private String errorMessageCheckout = "Sorry, that book is not available";
    private String successMessageReturn = "Thank you for returning the book";
    private String errorMessageReturn = "That is not a valid book to return";

    public Book(String title, String author, int yearPublished) {
        super();
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    public String getLibraryNumberCheckout() {
        return userWhoCheckedOut;
    }

    public void checkout(String libraryNumber) {
        if (isCheckout) {
            throw new CannotCheckOutCatalogElement(errorMessageCheckout);
        }
        isCheckout = true;
        userWhoCheckedOut = libraryNumber;
        System.out.println(successMessageCheckout);
    }

    public void returnCatalogElement() {
        if (!isCheckout) {
            throw new CannotReturnCatalogElement(errorMessageReturn);
        }
        isCheckout = false;
        userWhoCheckedOut = null;
        System.out.println(successMessageReturn);
    }

    public String getAuthor() {
        return author;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public String getUserWhoCheckedOut() {
        return userWhoCheckedOut;
    }

}
