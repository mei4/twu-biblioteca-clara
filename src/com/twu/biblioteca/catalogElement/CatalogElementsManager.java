package com.twu.biblioteca.catalogElement;

import com.twu.biblioteca.catalogElement.user.User;

import java.util.List;
import java.util.stream.IntStream;

public class CatalogElementsManager<T> {

    private String successMessageCheckout;
    private String errorMessageCheckout;
    private String successMessageReturn;
    private String errorMessageReturn;
    protected List<T> catalogElements;

    public CatalogElementsManager (List<T> catalogElements) {
        this.catalogElements = catalogElements;
    }

    public CatalogElementsManager (List<T> catalogElements,
                                   String successMessageCheckout, String errorMessageCheckout,
                                   String successMessageReturn, String errorMessageReturn) {
        this.catalogElements = catalogElements;
        this.successMessageCheckout = successMessageCheckout;
        this.errorMessageCheckout = errorMessageCheckout;
        this.successMessageReturn = successMessageReturn;
        this.errorMessageReturn = errorMessageReturn;
    }

    //TODO: delete
    public void showAll() {
        IntStream.range(0, catalogElements.size()).filter(i -> !((CatalogElement)catalogElements.get(i)).isCheckout())
                .forEach(i -> System.out.println(i + 1 + "- " + ((CatalogElement)catalogElements.get(i)).getDetails()));
    }

    //TODO: delete
    public void checkout(String bookReference, User loggedUser) {
        if (isValidReference(bookReference, errorMessageCheckout)) {
            int index = Integer.valueOf(bookReference) - 1;
            T catalogElement = catalogElements.get(index);
            if (((CatalogElement) catalogElement).isCheckout()) {
                System.out.println(errorMessageCheckout);
            }
            else {
                ((CatalogElement) catalogElement).setCheckout(true, loggedUser);
                System.out.println(successMessageCheckout);
            }
        }
    }

    //TODO: delete
    public void returnElement(String bookReference, User loggedUser) {
        if (isValidReference(bookReference, errorMessageReturn)) {
            int index = Integer.valueOf(bookReference) - 1;
            T catalogElement = catalogElements.get(index);
            if (!((CatalogElement)catalogElement).isCheckout()) {
                System.out.println(errorMessageReturn);
            }
            else {
                ((CatalogElement)catalogElement).setCheckout(false, null);
                System.out.println(successMessageReturn);
            }
        }
    }

    private boolean isValidReference(String bookReference, String errorMessage) {
        int index = 0;
        try {
            index = Integer.valueOf(bookReference) - 1;
        }
        catch (NumberFormatException e) {
            System.out.println(errorMessage);
            return false;
        }
        if (index < 0 || index >= catalogElements.size()) {
            System.out.println(errorMessage);
            return false;
        }
        return true;
    }

    public List<T> getAll() {
        return catalogElements;
    }

    public T getByReference(String reference) {
        if (isValidReference(reference, "Reference not valid")) {
            return catalogElements.get(Integer.valueOf(reference) - 1);
        }
        return null;
    }
}
