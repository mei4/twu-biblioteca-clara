package com.twu.biblioteca;

import java.util.List;
import java.util.stream.IntStream;

public class BooksManager<T extends CatalogElement> extends CatalogElementsManager<T> {

    public List<Book> books;
    private static final String SUCCESS_MESSAGE_CHECKOUT = "Thank you! Enjoy the book";
    private static final String ERROR_MESSAGE_CHECKOUT = "Sorry, that book is not available";
    private static final String SUCCESS_MESSAGE_RETURN = "Thank you for returning the book";
    private static final String ERROR_MESSAGE_RETURN = "That is not a valid book to return";


    public BooksManager(List<Book> books) {
        super((List<T>) books, SUCCESS_MESSAGE_CHECKOUT, ERROR_MESSAGE_CHECKOUT, SUCCESS_MESSAGE_RETURN, ERROR_MESSAGE_RETURN);
        this.books = books;
    }

    public void showAllCheckedOut() {
        IntStream.range(0, catalogElements.size()).filter(i -> catalogElements.get(i).isCheckout())
                .forEach(i -> System.out.println(i + 1 + "- " + catalogElements.get(i).getCheckoutDetails()));
    }

//    public void showAll() {
//        IntStream.range(0, books.size()).filter(i -> !books.get(i).isCheckout())
//                .forEach(i -> System.out.println(i + 1 + "- " + books.get(i).getDetails()));
//    }
//
//    public void checkout(String bookReference) {
//        if (isValidReference(bookReference, ERROR_MESSAGE_CHECKOUT)) {
//            int index = Integer.valueOf(bookReference) - 1;
//            Book book = books.get(index);
//            if (book.isCheckout()) {
//                System.out.println(ERROR_MESSAGE_CHECKOUT);
//            }
//            else {
//                book.setCheckout(true);
//                System.out.println(SUCCESS_MESSAGE_CHECKOUT);
//            }
//        }
//    }
//
//    public void returnElement(String bookReference) {
//        if (isValidReference(bookReference, ERROR_MESSAGE_RETURN)) {
//            int index = Integer.valueOf(bookReference) - 1;
//            Book book = books.get(index);
//            if (!book.isCheckout()) {
//                System.out.println(ERROR_MESSAGE_RETURN);
//            }
//            else {
//                book.setCheckout(false);
//                System.out.println(SUCCESS_MESSAGE_RETURN);
//            }
//        }
//    }
//
//    private boolean isValidReference(String bookReference, String errorMessage) {
//        int index = 0;
//        try {
//            index = Integer.valueOf(bookReference) - 1;
//        }
//        catch (NumberFormatException e) {
//            System.out.println(errorMessage);
//            return false;
//        }
//        if (index < 0 || index >= books.size()) {
//            System.out.println(errorMessage);
//            return false;
//        }
//        return true;
//    }
}
