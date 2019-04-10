package com.twu.biblioteca.catalogElement;

public class CannotReturnCatalogElement extends RuntimeException {

    public CannotReturnCatalogElement () {
        super("That is not a valid book to return");
    }
}
