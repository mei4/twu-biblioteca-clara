package com.twu.biblioteca.catalogElement;

import com.twu.biblioteca.catalogElement.user.User;

public abstract class CatalogElement {

    protected String title;
    protected boolean isCheckout;
    private User userCheckout;
    private String userWhoCheckedOut;

    //TODO: delete
    public void setCheckout(boolean isCheckout, User user) {
        this.isCheckout = isCheckout;
        this.setUserCheckout(user);
    }

    public boolean isCheckout() {
        return isCheckout;
    }

    //TODO: delete
    public abstract String getDetails();

    //TODO: delete
    public String getCheckoutDetails() {
        return getDetails() + " [Checked out by: " +  userCheckout.getLibraryNumber() + "]";
    }

    //TODO: delete
    private void setUserCheckout(User user) {
        userCheckout = user;
    }

    public void checkout(String libraryNumber) {
        if (isCheckout) {
            throw new CannotCheckOutCatalogElement();
        }
        isCheckout = true;
        userWhoCheckedOut = libraryNumber;
        System.out.println("Thank you! Enjoy the book");
    }

    public String getLibraryNumberCheckout() {
        return userWhoCheckedOut;
    }

    public void returnCatalogElement() {
        if (!isCheckout) {
            throw new CannotReturnCatalogElement();
        }
        isCheckout = false;
        userWhoCheckedOut = null;
        System.out.println("Thank you for returning the book");
    }

    public String getTitle() {
        return title;
    }
}
