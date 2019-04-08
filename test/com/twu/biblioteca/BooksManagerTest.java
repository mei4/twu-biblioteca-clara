package com.twu.biblioteca;


import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BooksManagerTest {

    private String successMessageCheckout = "Thank you! Enjoy the book";
    private String errorMessageCheckout = "Sorry, that book is not available";

    public List<Book> books = new ArrayList<>(Arrays.asList(
            new Book("To Kill a Mockingbird", "Harper Lee", 1988),
            new Book("Pride and Prejudice", "Jane Austen", 1813),
            new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925)));

//    @Test
//    public void checkThatAllTheBooksAreDisplayed() {
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(out));
//
//        BooksManager booksManager = new BooksManager(books);
//        booksManager.showAllBooks();
//
//        assertEquals(books + "\n", out.toString());
//
//    }

    @Test
    public void checkThatAllTheBooksAndTheirAuthorsAreDisplayed() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        BooksManager booksManager = new BooksManager(books);
        booksManager.showAllBooks();

        assertEquals("1- To Kill a Mockingbird | Harper Lee | 1988\n" +
                "2- Pride and Prejudice | Jane Austen | 1813\n" +
                "3- The Great Gatsby | F. Scott Fitzgerald | 1925\n", out.toString());
    }

    @Test
    public void checkThatABookCanBeCheckedOut() {
        BooksManager booksManager = new BooksManager(books);
        booksManager.checkoutBook("1");
        assertTrue(books.get(0).isCheckedOut());
    }

    @Test
    public void checkThatAConfirmationMessageIsDisplayedWhenBookCheckout() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        BooksManager booksManager = new BooksManager(books);
        booksManager.checkoutBook("1");

        assertEquals(successMessageCheckout + "\n", out.toString());
    }

    @Test
    public void checkThatAnErrorMessageIsDisplayedWhenCheckoutOfAnInvalidReference() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        BooksManager booksManager = new BooksManager(books);
        booksManager.checkoutBook("11");

        assertEquals(errorMessageCheckout + "\n", out.toString());
    }

    @Test
    public void checkThatAnErrorMessageIsDisplayedWhenReferenceNumberFormatException() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        BooksManager booksManager = new BooksManager(books);
        booksManager.checkoutBook("this");

        assertEquals(errorMessageCheckout + "\n", out.toString());
    }

    @Test
    public void checkThatCheckoutBooksAreNotInTheListOfAllBooks() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        books.get(1).setCheckedOut(); //Checkout Pride and Prejudice

        BooksManager booksManager = new BooksManager(books);
        booksManager.showAllBooks();

        assertEquals("1- To Kill a Mockingbird | Harper Lee | 1988\n" +
                "3- The Great Gatsby | F. Scott Fitzgerald | 1925\n", out.toString());
    }
}
