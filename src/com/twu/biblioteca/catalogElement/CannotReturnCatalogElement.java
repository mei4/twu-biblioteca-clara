package com.twu.biblioteca.catalogElement;

public class CannotReturnCatalogElement extends RuntimeException {

    public CannotReturnCatalogElement (String message) {
        super(message);
    }
}
