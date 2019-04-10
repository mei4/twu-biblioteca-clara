package com.twu.biblioteca.catalogElement;

public class CannotCheckOutCatalogElement extends RuntimeException {

    public CannotCheckOutCatalogElement() {
        super("Sorry, that book is not available");
    }
}
