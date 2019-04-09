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

    private String successMessageReturn = "Thank you for returning the book";
    private String errorMessageReturn = "That is not a valid book to return";

    public List<Book> books = new ArrayList<>(Arrays.asList(
            new Book("To Kill a Mockingbird", "Harper Lee", 1988),
            new Book("Pride and Prejudice", "Jane Austen", 1813),
            new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925)));

    User user1 = new User("ABC-1234", "nicePassword");
    User user2 = new User("XYZ-4321", "superNicePassword");

//    @Test
//    public void checkThatAllTheBooksAreDisplayed() {
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(out));
//
//        BooksManager booksManager = new BooksManager(books);
//        booksManager.showAll();
//
//        assertEquals(books + "\n", out.toString());
//
//    }

    @Test
    public void checkThatAllTheBooksAndTheirAuthorsAreDisplayed() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        BooksManager booksManager = new BooksManager(books);
        booksManager.showAll();

        assertEquals("1- To Kill a Mockingbird | Harper Lee | 1988\n" +
                "2- Pride and Prejudice | Jane Austen | 1813\n" +
                "3- The Great Gatsby | F. Scott Fitzgerald | 1925\n", out.toString());
    }

    @Test
    public void checkThatABookCanBeCheckedOut() {
        BooksManager booksManager = new BooksManager(books);
        booksManager.checkout("1");
        assertTrue(books.get(0).isCheckout());
    }

    @Test
    public void checkThatAConfirmationMessageIsDisplayedWhenBookCheckout() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        BooksManager booksManager = new BooksManager(books);
        booksManager.checkout("1");

        assertEquals(successMessageCheckout + "\n", out.toString());
    }

    @Test
    public void checkThatAnErrorMessageIsDisplayedWhenCheckoutOfUnavailableBook() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        books.get(0).setCheckout(true, null);
        BooksManager booksManager = new BooksManager(books);
        booksManager.checkout("1");

        assertEquals(errorMessageCheckout + "\n", out.toString());
    }

    @Test
    public void checkThatAnErrorMessageIsDisplayedWhenCheckoutOfAnInvalidReference() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        BooksManager booksManager = new BooksManager(books);
        booksManager.checkout("11");

        assertEquals(errorMessageCheckout + "\n", out.toString());
    }

    @Test
    public void checkThatAnErrorMessageIsDisplayedWhenCheckoutReferenceHasNumberFormatException() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        BooksManager booksManager = new BooksManager(books);
        booksManager.checkout("this");

        assertEquals(errorMessageCheckout + "\n", out.toString());
    }

    @Test
    public void checkThatCheckoutBooksAreNotInTheListOfAllBooks() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        books.get(1).setCheckout(true, null); //Checkout Pride and Prejudice

        BooksManager booksManager = new BooksManager(books);
        booksManager.showAll();

        assertEquals("1- To Kill a Mockingbird | Harper Lee | 1988\n" +
                "3- The Great Gatsby | F. Scott Fitzgerald | 1925\n", out.toString());
    }

    @Test
    public void checkThatReturnedBooksAreInTheListOfAllBooks() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        books.get(1).setCheckout(true, null); //Checkout Pride and Prejudice
        books.get(0).setCheckout(true, null);
        BooksManager booksManager = new BooksManager(books);
        booksManager.returnElement("2"); //Return Pride and Prejudice

        booksManager.showAll();

        assertEquals("Thank you for returning the book\n" +
                "2- Pride and Prejudice | Jane Austen | 1813\n" +
                "3- The Great Gatsby | F. Scott Fitzgerald | 1925\n", out.toString());
    }

    @Test
    public void checkThatAConfirmationMessageIsDisplayedWhenBookReturned() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        books.get(0).setCheckout(true, null);
        BooksManager booksManager = new BooksManager(books);
        booksManager.returnElement("1");

        assertEquals(successMessageReturn + "\n", out.toString());
    }

    @Test
    public void checkThatAnErrorMessageIsDisplayedWhenReturnOfAnInvalidReference() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        BooksManager booksManager = new BooksManager(books);
        booksManager.returnElement("11");

        assertEquals(errorMessageReturn + "\n", out.toString());
    }

    @Test
    public void checkThatAnErrorMessageIsDisplayedWhenReturnReferenceHasNumberFormatException() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        BooksManager booksManager = new BooksManager(books);
        booksManager.returnElement("this");

        assertEquals(errorMessageReturn + "\n", out.toString());
    }

    @Test
    public void checkThatAnErrorMessageIsDisplayedWhenReturningAvailableBook() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        BooksManager booksManager = new BooksManager(books);
        booksManager.returnElement("1");

        assertEquals(errorMessageReturn + "\n", out.toString());
    }

    @Test
    public void checkThatCheckoutBooksAreInTheListOfAllCheckoutBooks() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        books.get(0).setCheckout(true, user1); //Checkout To Kill a Mockingbird
        books.get(2).setCheckout(true, user2); //Checkout The Great Gatsby
        BooksManager booksManager = new BooksManager(books);

        booksManager.showAllCheckedOut();

        assertEquals("1- To Kill a Mockingbird | Harper Lee | 1988 [Checked out by: ABC-1234]\n" +
                "3- The Great Gatsby | F. Scott Fitzgerald | 1925 [Checked out by: XYZ-4321]\n", out.toString());
    }
}
