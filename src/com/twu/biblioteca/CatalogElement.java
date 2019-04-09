package com.twu.biblioteca;

public abstract class CatalogElement {

    protected String title;
    protected boolean isCheckout;
    private User userCheckout;


    public void setCheckout(boolean isCheckout, User user) {
        this.isCheckout = isCheckout;
        this.setUserCheckout(user);
    }

    public boolean isCheckout() {
        return isCheckout;
    }

    public abstract String getDetails();

    public String getCheckoutDetails() {
        return getDetails() + " [Checked out by: " +  userCheckout.getLibraryNumber() + "]";
    }

    private void setUserCheckout(User user) {
        userCheckout = user;
    }
}
