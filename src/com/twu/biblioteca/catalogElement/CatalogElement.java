package com.twu.biblioteca.catalogElement;

import com.twu.biblioteca.user.User;

public abstract class CatalogElement {

    protected String title;
    protected boolean isCheckout;
    private User userCheckout;
    private String userWhoCheckedOut;

    private String successMessageCheckout;
    private String errorMessageCheckout;
    private String successMessageReturn;
    private String errorMessageReturn;

    public CatalogElement() {}

    public CatalogElement(String successMessageCheckout, String errorMessageCheckout,
                          String successMessageReturn, String errorMessageReturn) {
        this.successMessageCheckout = successMessageCheckout;
        this.errorMessageCheckout = errorMessageCheckout;
        this.successMessageReturn = successMessageReturn;
        this.errorMessageReturn = errorMessageReturn;
    }

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
            throw new CannotCheckOutCatalogElement(errorMessageCheckout);
        }
        isCheckout = true;
        userWhoCheckedOut = libraryNumber;
        System.out.println(successMessageCheckout);
    }

    public String getLibraryNumberCheckout() {
        return userWhoCheckedOut;
    }

    public void returnCatalogElement() {
        if (!isCheckout) {
            throw new CannotReturnCatalogElement(errorMessageReturn);
        }
        isCheckout = false;
        userWhoCheckedOut = null;
        System.out.println(successMessageReturn);
    }

    public String getTitle() {
        return title;
    }
}
