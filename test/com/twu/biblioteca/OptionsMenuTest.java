package com.twu.biblioteca;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class OptionsMenuTest {

    private String option1 = "List of books";
    private String option2 = "Checkout a book";
    private String option3 = "Return a book";
    private String option4 = "List of movies";
    private String option5 = "Checkout a movie";
    private String option6 = "View books checked out";
    private String option7 = "Quit";

    private String errorInvalidOption = "Please select a valid option";

    List<Book> books = new ArrayList<>(Arrays.asList(
            new Book("To Kill a Mockingbird", "Harper Lee", 1988),
            new Book("Pride and Prejudice", "Jane Austen", 1813),
            new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925)));
    BooksManager booksManager = new BooksManager(books);

    public List<Movie> movies = new ArrayList<>(Arrays.asList(
            new Movie("Totoro", 1988, "Hayao Miyazaki", 10),
            new Movie("The Lion King", 1994, "Rob Minkoff and Roger Allers", 8),
            new Movie("Captain Marvel", 2019, "Anna Boden and Ryan Fleck", 0)));
    MoviesManager moviesManager = new MoviesManager(movies);

    User user1 = new User("ABC-1234", "nicePassword");
    User user2 = new User("XYZ-4321", "superNicePassword");

    @Test
    public void checkThatNothingIsShowedWhenEmptyMenu() {
        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(), null, null);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        optionsMenu.showMenu();

        assertEquals( "", out.toString());
    }

    @Test
    public void checkThatTheMenuIsPrintedWhenOneOption() {
        List<String> options = new ArrayList<>(Arrays.asList(option1));
        OptionsMenu optionsMenu = new OptionsMenu(options, null, null);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        optionsMenu.showMenu();

        assertEquals("1- " + option1 + "\n", out.toString());
    }

    @Test
    public void checkThatAnErrorMessageIsThrownWhenInvalidOptionSelected() {
        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(Arrays.asList(option1)), null, null);
        System.setIn(new ByteArrayInputStream("2".getBytes()));
        optionsMenu.showMenu();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        optionsMenu.manageOptionSelectedByTheUser();

        assertEquals(errorInvalidOption + "\n", out.toString());
    }

    @Test
    public void checkThatAnErrorMessageIsNotThrownWhenValidOptionSelected() {
        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(Arrays.asList(option1)),
                new BooksManager(new ArrayList<>()), null);
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        optionsMenu.showMenu();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        optionsMenu.manageOptionSelectedByTheUser();

        assertNotEquals(errorInvalidOption + "\n", out.toString());
    }

    @Test
    public void checkThatAnErrorMessageIsThrownWhenNumberFormatException() {
        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(Arrays.asList(option1)), null, null);
        System.setIn(new ByteArrayInputStream("List of fiction books".getBytes()));
        optionsMenu.showMenu();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        optionsMenu.manageOptionSelectedByTheUser();

        assertEquals(errorInvalidOption + "\n", out.toString());
    }

    @Test
    public void checkThatAllBooksAreDisplayedAfterSelectingTheOption() {
        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(Arrays.asList(option1)), booksManager, null);
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        optionsMenu.showMenu();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        optionsMenu.manageOptionSelectedByTheUser();

        assertEquals("1- To Kill a Mockingbird | Harper Lee | 1988\n" +
                "2- Pride and Prejudice | Jane Austen | 1813\n" +
                "3- The Great Gatsby | F. Scott Fitzgerald | 1925\n" +
                "------------------\n" +
                "1- " + option1 + "\n", out.toString());
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
    public void checkThatABookIsCheckoutAfterSelectingTheOption() {
        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(Arrays.asList(option1, option2)), booksManager, null);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("2\n1".getBytes());
        System.setIn(byteArrayInputStream);
        optionsMenu.showMenu();
        optionsMenu.manageOptionSelectedByTheUser();

        assertTrue(books.get(0).isCheckout());
        assertFalse(books.get(1).isCheckout());
        assertFalse(books.get(2).isCheckout());
    }

    @Test
    public void checkThatABookIsReturnedAfterSelectingTheOption() {
        booksManager.checkout("1");
        booksManager.checkout("2");
        assertTrue(books.get(0).isCheckout());
        assertTrue(books.get(1).isCheckout());

        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(Arrays.asList(option1, option2, option3)),
                booksManager, null);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("3\n2".getBytes());
        System.setIn(byteArrayInputStream);
        optionsMenu.showMenu();
        optionsMenu.manageOptionSelectedByTheUser();

        assertTrue(books.get(0).isCheckout());
        assertFalse(books.get(1).isCheckout());
        assertFalse(books.get(2).isCheckout());
    }

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void checkThatTheApplicationIsClosedAfterSelectingTheOption() {
        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(Arrays.asList(option1, option2, option3, option7)),
                booksManager, null);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("4".getBytes());
        System.setIn(byteArrayInputStream);
        optionsMenu.showMenu();
        exit.expectSystemExit();
        optionsMenu.manageOptionSelectedByTheUser();
    }

    @Test
    public void checkThatTheApplicationKeepsShowingTheMenuIfUserDoesNotQuit() {
        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(Arrays.asList(option1, option2, option3, option7)),
                booksManager, null);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("2\n2\n".getBytes());
        System.setIn(byteArrayInputStream);
        optionsMenu.showMenu();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        optionsMenu.manageOptionSelectedByTheUser();

        assertEquals("Please, type the reference of the book:\n" +
                "Thank you! Enjoy the book\n" +
                "------------------\n" +
                "1- " + option1 + "\n" +
                "2- " + option2 + "\n" +
                "3- " + option3 + "\n" +
                "4- " + option7 + "\n", out.toString());
    }

    @Test
    public void checkThatAllMoviesAreDisplayedAfterSelectingTheOption() {
        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(Arrays.asList(option1, option2, option3, option4,
                option7)), booksManager, moviesManager);

        System.setIn(new ByteArrayInputStream("4".getBytes()));
        optionsMenu.showMenu();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        optionsMenu.manageOptionSelectedByTheUser();

        //After listing the menu will be displayed again automatically
        assertEquals("1- Totoro | 1988 | Hayao Miyazaki | 10\n" +
                "2- The Lion King | 1994 | Rob Minkoff and Roger Allers | 8\n" +
                "3- Captain Marvel | 2019 | Anna Boden and Ryan Fleck | unrated\n" +
                "------------------\n" +
                "1- List of books\n" +
                "2- Checkout a book\n" +
                "3- Return a book\n" +
                "4- List of movies\n" +
                "5- Quit\n", out.toString());
    }

    @Test
    public void checkThatAMovieIsCheckoutAfterSelectingTheOption() {
        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(Arrays.asList(option1, option2, option3, option4,
                option5, option7)), booksManager, moviesManager);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("5\n1".getBytes());
        System.setIn(byteArrayInputStream);

        optionsMenu.showMenu();
        optionsMenu.manageOptionSelectedByTheUser();

        assertTrue(movies.get(0).isCheckout());
        assertFalse(movies.get(1).isCheckout());
        assertFalse(movies.get(2).isCheckout());
    }

    @Test
    public void checkThatAllCheckedOutBooksAreDisplayedAfterSelectingTheOption() {
        books.get(0).setCheckout(true, user1);
        books.get(2).setCheckout(true, user2);

        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(Arrays.asList(option1, option2, option3, option4,
                option5, option6, option7)), booksManager, moviesManager);
        System.setIn(new ByteArrayInputStream("6".getBytes()));
        optionsMenu.showMenu();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        optionsMenu.manageOptionSelectedByTheUser();

        assertEquals("1- To Kill a Mockingbird | Harper Lee | 1988 [Checked out by: ABC-1234]\n" +
                "3- The Great Gatsby | F. Scott Fitzgerald | 1925 [Checked out by: XYZ-4321]\n" +
                "------------------\n" +
                "1- List of books\n" +
                "2- Checkout a book\n" +
                "3- Return a book\n" +
                "4- List of movies\n" +
                "5- Checkout a movie\n" +
                "6- View books checked out\n" +
                "7- Quit\n", out.toString());
    }
}
