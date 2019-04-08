package com.twu.biblioteca;

public abstract class CatalogElement {

    protected String title;
    protected boolean isCheckout;

    public void setCheckout(boolean isCheckout) {
        this.isCheckout = isCheckout;
    }

    public boolean isCheckout() {
        return isCheckout;
    }

    public abstract String getDetails();
}
