package com.twu.biblioteca;

import java.util.List;
import java.util.stream.IntStream;

public class BooksManager {

    public List<Book> books;
    private static final String SUCCESS_MESSAGE_CHECKOUT = "Thank you! Enjoy the book";
    private static final String ERROR_MESSAGE_CHECKOUT = "Sorry, that book is not available";
    private static final String SUCCESS_MESSAGE_RETURN = "Thank you for returning the book";

    public BooksManager(List<Book> books) {
        this.books = books;
    }

    public void showAllBooks() {
        IntStream.range(0, books.size()).filter(i -> !books.get(i).isCheckout())
                .forEach(i -> System.out.println(i + 1 + "- " + books.get(i).getDetails()));
    }

    public void checkoutBook(String bookReference) {
        int index = 0;
        try {
            index = Integer.valueOf(bookReference) - 1;
        }
        catch (NumberFormatException e) {
            System.out.println(ERROR_MESSAGE_CHECKOUT);
            return;
        }
        if (index < 0 || index >= books.size()) {
            System.out.println(ERROR_MESSAGE_CHECKOUT);
        }
        else {
            Book book = books.get(index);
            book.setCheckout(true);
            System.out.println(SUCCESS_MESSAGE_CHECKOUT);
        }

    }

    public void returnBook(String bookReference) {
        int index = Integer.valueOf(bookReference) - 1;
        Book book = books.get(index);
        book.setCheckout(false);
        System.out.println(SUCCESS_MESSAGE_RETURN);
    }
}
