package com.twu.biblioteca.catalogElement.book;


import com.twu.biblioteca.user.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BooksManagerTest {

    private String successMessageCheckout = "Thank you! Enjoy the book";
    private String errorMessageCheckout = "Sorry, that book is not available";

    private String successMessageReturn = "Thank you for returning the book";
    private String errorMessageReturn = "That is not a valid book to return";

    public List<Book> books = new ArrayList<>(Arrays.asList(
            new Book("To Kill a Mockingbird", "Harper Lee", 1988),
            new Book("Pride and Prejudice", "Jane Austen", 1813),
            new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925)));

    User user1 = new User("ABC-1234", "nicePassword", null, null, null);
    User user2 = new User("XYZ-4321", "superNicePassword", null, null, null);

}
