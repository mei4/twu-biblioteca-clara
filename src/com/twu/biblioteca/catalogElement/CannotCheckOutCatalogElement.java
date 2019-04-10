package com.twu.biblioteca.catalogElement;

public class CannotCheckOutCatalogElement extends RuntimeException {

    public CannotCheckOutCatalogElement(String message) {
        super(message);
    }
}
