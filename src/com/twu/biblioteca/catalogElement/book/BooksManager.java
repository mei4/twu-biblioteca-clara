package com.twu.biblioteca.catalogElement.book;

import com.twu.biblioteca.catalogElement.CatalogElement;
import com.twu.biblioteca.catalogElement.CatalogElementsManager;

import java.util.List;
import java.util.stream.IntStream;

public class BooksManager<T extends CatalogElement> extends CatalogElementsManager<T> {

    public List<Book> books; //TODO: delete
    //TODO: delete
    private static final String SUCCESS_MESSAGE_CHECKOUT = "Thank you! Enjoy the book";
    private static final String ERROR_MESSAGE_CHECKOUT = "Sorry, that book is not available";
    private static final String SUCCESS_MESSAGE_RETURN = "Thank you for returning the book";
    private static final String ERROR_MESSAGE_RETURN = "That is not a valid book to return";


    public BooksManager(List<Book> books) {
        super((List<T>) books, SUCCESS_MESSAGE_CHECKOUT, ERROR_MESSAGE_CHECKOUT, SUCCESS_MESSAGE_RETURN, ERROR_MESSAGE_RETURN); //TODO: change constructor
        this.books = books; //TODO: delete
    }

    //TODO: delete
    public void showAllCheckedOut() {
        IntStream.range(0, catalogElements.size()).filter(i -> catalogElements.get(i).isCheckout())
                .forEach(i -> System.out.println(i + 1 + "- " + catalogElements.get(i).getCheckoutDetails()));
    }
}
