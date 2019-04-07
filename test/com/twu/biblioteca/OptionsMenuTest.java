package com.twu.biblioteca;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class OptionsMenuTest {

    private String option1 = "List of books";
    private String option2 = "Checkout a book";
    private String errorInvalidOption = "Please select a valid option";

    List<Book> books = new ArrayList<>(Arrays.asList(
            new Book("To Kill a Mockingbird", "Harper Lee", 1988),
            new Book("Pride and Prejudice", "Jane Austen", 1813),
            new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925)));
    BooksManager booksManager = new BooksManager(books);

    @Test
    public void checkThatNothingIsShowedWhenEmptyMenu() {
        List<String> options = new ArrayList<>();
        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(), null);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        optionsMenu.showMenu();

        assertEquals( "", out.toString());
    }

    @Test
    public void checkThatTheMenuIsPrintedWhenOneOption() {
        List<String> options = new ArrayList<>(Arrays.asList(option1));
        OptionsMenu optionsMenu = new OptionsMenu(options, null);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        optionsMenu.showMenu();

        assertEquals("1- " + option1 + "\n", out.toString());
    }

    @Test
    public void checkThatAnErrorMessageIsThrownWhenInvalidOptionSelected() throws IOException {
        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(Arrays.asList(option1)), null);
        System.setIn(new ByteArrayInputStream("2".getBytes()));
        optionsMenu.showMenu();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        optionsMenu.manageOptionSelectedByTheUser();

        assertEquals(errorInvalidOption + "\n", out.toString());
    }

    @Test
    public void checkThatAnErrorMessageIsNotThrownWhenValidOptionSelected() throws IOException {
        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(Arrays.asList(option1)),
                new BooksManager(new ArrayList<>()));
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        optionsMenu.showMenu();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        optionsMenu.manageOptionSelectedByTheUser();

        assertNotEquals(errorInvalidOption + "\n", out.toString());
    }

    @Test
    public void checkThatAnErrorMessageIsThrownWhenNumberFormatException() throws IOException {
        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(Arrays.asList(option1)), null);
        System.setIn(new ByteArrayInputStream("List of fiction books".getBytes()));
        optionsMenu.showMenu();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        optionsMenu.manageOptionSelectedByTheUser();

        assertEquals(errorInvalidOption + "\n", out.toString());
    }

    @Test
    public void checkThatAllBooksAreDisplayedAfterSelectingTheOption() throws IOException {
        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(Arrays.asList(option1)), booksManager);
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        optionsMenu.showMenu();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        optionsMenu.manageOptionSelectedByTheUser();

        assertEquals("1- To Kill a Mockingbird | Harper Lee | 1988\n" +
                "2- Pride and Prejudice | Jane Austen | 1813\n" +
                "3- The Great Gatsby | F. Scott Fitzgerald | 1925\n", out.toString());
    }

//    @Test
//    public void checkThatCheckoutOptionIsSelected() throws IOException {
//        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(Arrays.asList(option1, option2)), booksManager);
//        System.setIn(new ByteArrayInputStream("2".getBytes()));
//        optionsMenu.showMenu();
//
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(out));
//
//        optionsMenu.manageOptionSelectedByTheUser();
//
//        assertEquals("Please, type the reference of the book:\n", out.toString());
//    }

    @Test
    public void checkThatABookIsCheckoutAfterSelectingTheOption() throws IOException {
        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(Arrays.asList(option1, option2)), booksManager);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("2\n1".getBytes());
        System.setIn(byteArrayInputStream);
        optionsMenu.showMenu();
        optionsMenu.manageOptionSelectedByTheUser();

        assertTrue(books.get(0).isCheckedOut());
    }


}
