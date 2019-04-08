package com.twu.biblioteca;

import java.util.List;
import java.util.stream.IntStream;

public class CatalogElementsManager<T> {

    private String successMessageCheckout;
    private String errorMessageCheckout;
    private String successMessageReturn;
    private String errorMessageReturn;
    private List<CatalogElement> catalogElements;

    public CatalogElementsManager (List<T> catalogElements,
                                   String successMessageCheckout, String errorMessageCheckout,
                                   String successMessageReturn, String errorMessageReturn) {
        this.catalogElements = (List<CatalogElement>) catalogElements;
        this.successMessageCheckout = successMessageCheckout;
        this.errorMessageCheckout = errorMessageCheckout;
        this.successMessageReturn = successMessageReturn;
        this.errorMessageReturn = errorMessageReturn;
    }

    public void showAll() {
        IntStream.range(0, catalogElements.size()).filter(i -> !catalogElements.get(i).isCheckout())
                .forEach(i -> System.out.println(i + 1 + "- " + catalogElements.get(i).getDetails()));
    }

    public void checkout(String bookReference) {
        if (isValidReference(bookReference, errorMessageCheckout)) {
            int index = Integer.valueOf(bookReference) - 1;
            CatalogElement catalogElement = catalogElements.get(index);
            if (catalogElement.isCheckout()) {
                System.out.println(errorMessageCheckout);
            }
            else {
                catalogElement.setCheckout(true);
                System.out.println(successMessageCheckout);
            }
        }
    }

    public void returnElement(String bookReference) {
        if (isValidReference(bookReference, errorMessageReturn)) {
            int index = Integer.valueOf(bookReference) - 1;
            CatalogElement catalogElement = catalogElements.get(index);
            if (!catalogElement.isCheckout()) {
                System.out.println(errorMessageReturn);
            }
            else {
                catalogElement.setCheckout(false);
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
}
