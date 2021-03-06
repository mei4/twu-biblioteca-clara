package com.twu.biblioteca;

import com.twu.biblioteca.catalogElement.book.Book;
import com.twu.biblioteca.catalogElement.book.BooksManager;
import com.twu.biblioteca.catalogElement.movie.Movie;
import com.twu.biblioteca.catalogElement.movie.MoviesManager;
import com.twu.biblioteca.user.User;
import com.twu.biblioteca.user.UserAccountsManager;
import com.twu.biblioteca.user.exception.NoUserLoggedIn;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OptionsMenuTest {

    private String option1 = "List of books";
    private String option2 = "Checkout a book";
    private String option3 = "Return a book";
    private String option4 = "List of movies";
    private String option5 = "Checkout a movie";
    private String option6 = "View books checked out";
    private String option7 = "Login";
    private String option8 = "View my information";
    private String option9 = "Quit";

    private String errorInvalidOption = "Please select a valid option";

    private Book mockedBook1;
    private Book mockedBook2;
    private Book mockedBook3;

    List<Book> mockedBooks = new ArrayList<>(Arrays.asList(
            new Book("To Kill a Mockingbird", "Harper Lee", 1988),
            new Book("Pride and Prejudice", "Jane Austen", 1813),
            new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925)));
    BooksManager mockedBooksManager = new BooksManager(mockedBooks);

    public List<Movie> movies = new ArrayList<>(Arrays.asList(
            new Movie("Totoro", 1988, "Hayao Miyazaki", 10),
            new Movie("The Lion King", 1994, "Rob Minkoff and Roger Allers", 8),
            new Movie("Captain Marvel", 2019, "Anna Boden and Ryan Fleck", 0)));
    MoviesManager moviesManager = new MoviesManager(movies);
    MoviesManager mockMoviesManager;

    User mockUser1;
    User mockUser2;
    User user1 = new User("ABC-1234", "nicePassword", null, null, null);
    User user2 = new User("XYZ-4321", "superNicePassword", null, null, null);

    List<User> users = new ArrayList<>(Arrays.asList(
            new User("ABC-1234", "nicePassword", null, null, null),
            new User("XYZ-4321", "superNicePassword", null, null, null)));
    UserAccountsManager userAccountsManager = new UserAccountsManager(users);
    UserAccountsManager mockUserAccountsManager;


    @Before
    public void initialize() {
//        mockedBook1 = mock(Book.class);
//        mockedBook2 = mock(Book.class);
//        mockedBook3 = mock(Book.class);
//        mockedBooks = mock(List.class);
//        when(mockedBooks.get(0)).thenReturn(mockedBook1);
//        when(mockedBooks.get(1)).thenReturn(mockedBook2);
//        when(mockedBooks.get(2)).thenReturn(mockedBook3);
//        mockedBooksManager = mock(BooksManager.class);

        mockUser1 = mock(User.class);
        when(mockUser1.getLibraryNumber()).thenReturn("ABC");
        mockUser2 = mock(User.class);
        when(mockUser2.getLibraryNumber()).thenReturn("XYZ");
        userAccountsManager.logIn("XYZ-4321", "superNicePassword");
        mockUserAccountsManager = mock(UserAccountsManager.class);
        mockMoviesManager = mock(MoviesManager.class);
    }

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void checkThatNothingIsShowedWhenEmptyMenu() {
        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(), null, null, null);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        optionsMenu.showMenu();

        assertEquals( "", out.toString());
    }

    @Test
    public void checkThatTheMenuIsPrintedWhenOneOption() {
        List<String> options = new ArrayList<>(Arrays.asList(option1));
        OptionsMenu optionsMenu = new OptionsMenu(options, null, null, null);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        optionsMenu.showMenu();

        assertEquals("1- " + option1 + "\n", out.toString());
    }

    @Test
    public void checkThatAnErrorMessageIsThrownWhenInvalidOptionSelected() {
        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(Arrays.asList(option1)),
                null, null, null);
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
                new BooksManager(new ArrayList<>()), null, null);
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        optionsMenu.showMenu();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        optionsMenu.manageOptionSelectedByTheUser();

        assertNotEquals(errorInvalidOption + "\n", out.toString());
    }

    @Test
    public void checkThatAnErrorMessageIsThrownWhenNumberFormatException() {
        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(Arrays.asList(option1)), null, null, null);
        System.setIn(new ByteArrayInputStream("List of fiction books".getBytes()));
        optionsMenu.showMenu();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        optionsMenu.manageOptionSelectedByTheUser();

        assertEquals(errorInvalidOption + "\n", out.toString());
    }

    @Test
    public void checkThatAllBooksAreDisplayedAfterSelectingTheOption() {
        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(Arrays.asList(option1)), mockedBooksManager, null, null);
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

    @Test
    public void checkThatABookIsCheckoutAfterSelectingTheOption() {
        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(Arrays.asList(option1, option2)), mockedBooksManager,
                null, userAccountsManager);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("2\n1".getBytes());
        System.setIn(byteArrayInputStream);
        optionsMenu.showMenu();
        optionsMenu.manageOptionSelectedByTheUser();

        assertTrue(mockedBooks.get(0).isCheckout());
        assertFalse(mockedBooks.get(1).isCheckout());
        assertFalse(mockedBooks.get(2).isCheckout());
    }

    @Test
    public void checkThatABookIsReturnedAfterSelectingTheOption() {
        mockedBooksManager.checkout("1", mockUser1);
        mockedBooksManager.checkout("2", mockUser2);
        assertTrue(mockedBooks.get(0).isCheckout());
        assertTrue(mockedBooks.get(1).isCheckout());

        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(Arrays.asList(option1, option2, option3)),
                mockedBooksManager, null, userAccountsManager);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("3\n2".getBytes());
        System.setIn(byteArrayInputStream);
        optionsMenu.showMenu();
        optionsMenu.manageOptionSelectedByTheUser();

        assertTrue(mockedBooks.get(0).isCheckout());
        assertFalse(mockedBooks.get(1).isCheckout());
        assertFalse(mockedBooks.get(2).isCheckout());
    }

    @Test
    public void checkThatTheApplicationIsClosedAfterSelectingTheOption() {
        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(Arrays.asList(option1, option2, option3, option9)),
                mockedBooksManager, null, null);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("4".getBytes());
        System.setIn(byteArrayInputStream);
        optionsMenu.showMenu();
        exit.expectSystemExit();
        optionsMenu.manageOptionSelectedByTheUser();
    }

    @Test
    public void checkThatTheApplicationKeepsShowingTheMenuIfUserDoesNotQuit() {
        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(Arrays.asList(option1, option2, option3, option9)),
                mockedBooksManager, null, userAccountsManager);
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
                "4- " + option9 + "\n", out.toString());
    }

    @Test
    public void checkThatAllMoviesAreDisplayedAfterSelectingTheOption() {
        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(Arrays.asList(option1, option2, option3, option4,
                option9)), mockedBooksManager, moviesManager, null);

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
                option5, option9)), mockedBooksManager, moviesManager, mockUserAccountsManager);
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
        userAccountsManager.logIn("ABC-1234","nicePassword");

        mockedBooks.get(0).checkout("ABC-1234");
        mockedBooks.get(2).checkout("XYZ-4321");

        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(Arrays.asList(option1, option2, option3, option4,
                option5, option6, option9)), mockedBooksManager, moviesManager, userAccountsManager);
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

    @Test
    public void checkThatAUserIsLoggedInAfterSelectingTheOption() {
        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(Arrays.asList(option1, option2, option3, option4,
                option5, option6, option7, option9)), mockedBooksManager, moviesManager, userAccountsManager);
        System.setIn(new ByteArrayInputStream("7\nABC-1234\nnicePassword".getBytes()));

        optionsMenu.showMenu();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        optionsMenu.manageOptionSelectedByTheUser();

        assertEquals("Library number:\n" +
                "Password:\n" +
                "Successful login\n" +
                "------------------\n" +
                "1- List of books\n" +
                "2- Checkout a book\n" +
                "3- Return a book\n" +
                "4- List of movies\n" +
                "5- Checkout a movie\n" +
                "6- View books checked out\n" +
                "7- Login\n" +
                "8- Quit\n", out.toString());
    }

    @Test
    public void checkThatAMessageIsShownAfterSelectingTheCheckoutBookOptionWithoutLogin() {
        exceptionRule.expect(NoUserLoggedIn.class);

        UserAccountsManager userAccountsManagerWithoutLogin = new UserAccountsManager(users);

        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(Arrays.asList(option1, option2)),
                mockedBooksManager, moviesManager, userAccountsManagerWithoutLogin);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("2".getBytes());
        System.setIn(byteArrayInputStream);

        optionsMenu.showMenu();
        optionsMenu.manageOptionSelectedByTheUser();
    }

    @Test
    public void checkThatAMessageIsShownAfterSelectingTheReturnBookOptionWithoutLogin() {
        exceptionRule.expect(NoUserLoggedIn.class);

        UserAccountsManager userAccountsManagerWithoutLogin = new UserAccountsManager(users);

        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(Arrays.asList(option1, option2, option3)),
                mockedBooksManager, moviesManager, userAccountsManagerWithoutLogin);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("3".getBytes());
        System.setIn(byteArrayInputStream);

        optionsMenu.showMenu();
        optionsMenu.manageOptionSelectedByTheUser();
    }

    @Test
    public void checkThatAMessageIsShownAfterSelectingViewMyInformationWithoutLogin() {
        exceptionRule.expect(NoUserLoggedIn.class);

        UserAccountsManager userAccountsManagerWithoutLogin = new UserAccountsManager(users);

        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(Arrays.asList(option1, option2, option3, option4,
                option5, option6, option7, option8, option9)), mockedBooksManager, moviesManager, userAccountsManagerWithoutLogin);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("8".getBytes());

        System.setIn(byteArrayInputStream);
        optionsMenu.showMenu();

        optionsMenu.manageOptionSelectedByTheUser();
    }
}
