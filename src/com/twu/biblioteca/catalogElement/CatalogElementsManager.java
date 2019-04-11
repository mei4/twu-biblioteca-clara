package com.twu.biblioteca.catalogElement;

import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

public class CatalogElementsManager {

    protected List<? extends CatalogElement> catalogElements;

    public CatalogElementsManager (List<? extends CatalogElement> catalogElements) {
        this.catalogElements = catalogElements;
    }

    public HashMap<Integer, ? extends CatalogElement> getAllAvailable() {
        HashMap<Integer, CatalogElement> checkedOutElements = new HashMap<>();
        IntStream.range(0, catalogElements.size())
                .filter(i -> !catalogElements.get(i).isCheckout())
                .forEach(i -> checkedOutElements.put(new Integer(i + 1), catalogElements.get(i)));
        return checkedOutElements;
    }

    public CatalogElement getByReference(String reference) {
        if (isValidReference(reference, "Reference not valid")) {
            return catalogElements.get(Integer.valueOf(reference) - 1);
        }
        return null;
    }

    public HashMap<Integer, ? extends CatalogElement> getAllCheckedOut() {
        HashMap<Integer, CatalogElement> checkedOutElements = new HashMap<>();
        IntStream.range(0, catalogElements.size())
                .filter(i -> catalogElements.get(i).isCheckout())
                .forEach(i -> {
                    checkedOutElements.put(new Integer(i + 1), catalogElements.get(i));
                });
        return checkedOutElements;
    }

    protected boolean isValidReference(String bookReference, String errorMessage) {
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
