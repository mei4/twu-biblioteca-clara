package com.twu.biblioteca.catalogElement;

public abstract class CatalogElement {

    protected String title;
    protected boolean isCheckout;

    public CatalogElement() {}

    public boolean isCheckout() {
        return isCheckout;
    }


    public String getTitle() {
        return title;
    }
}
