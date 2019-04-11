package com.twu.biblioteca.catalogElement.book;

import com.twu.biblioteca.catalogElement.CatalogElementsManager;
import com.twu.biblioteca.user.User;

import java.util.List;

public class BooksManager extends CatalogElementsManager {

    private static final String SUCCESS_MESSAGE_CHECKOUT = "Thank you! Enjoy the book";
    private static final String ERROR_MESSAGE_CHECKOUT = "Sorry, that book is not available";
    //TODO: delete
    private static final String SUCCESS_MESSAGE_RETURN = "Thank you for returning the book";
    private static final String ERROR_MESSAGE_RETURN = "That is not a valid book to return";


    public BooksManager(List<Book> books) {
        super(books);
    }

    public void checkout(String reference, User loggedUser) {
        if (isValidReference(reference, ERROR_MESSAGE_CHECKOUT)) {
            int index = Integer.valueOf(reference) - 1;
            Book book = (Book) catalogElements.get(index);
            book.checkout(loggedUser.getLibraryNumber());
        }
    }
}
