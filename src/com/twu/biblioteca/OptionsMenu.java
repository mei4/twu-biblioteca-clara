package com.twu.biblioteca;

import com.twu.biblioteca.catalogElement.book.Book;
import com.twu.biblioteca.catalogElement.book.BooksManager;
import com.twu.biblioteca.catalogElement.movie.Movie;
import com.twu.biblioteca.catalogElement.movie.MoviesManager;
import com.twu.biblioteca.user.User;
import com.twu.biblioteca.user.UserAccountsManager;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class OptionsMenu {

    public static final String ERROR_INVALID_OPTION = "Please select a valid option";
    private List<String> options;
    private BooksManager booksManager;
    private MoviesManager moviesManager;
    private UserAccountsManager userAccountsManager;

    public OptionsMenu(List<String> options, BooksManager booksManager, MoviesManager moviesManager, UserAccountsManager userAccountsManager) {
        this.options = options;
        this.booksManager = booksManager;
        this.moviesManager = moviesManager;
        this.userAccountsManager = userAccountsManager;
    }

    public void showMenu() {
        IntStream.range(0, options.size()).forEach(i -> System.out.println(i + 1 + "- " + options.get(i)));
    }

    public void manageOptionSelectedByTheUser() {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            String optionIndex = scanner.nextLine();
            if (isValidOptionInput(optionIndex)) {
                String optionName = options.get(Integer.valueOf(optionIndex) - 1);
                switch (optionName) {
                    case "List of books":
                        HashMap<Integer, Book> availableBooks = (HashMap<Integer, Book>) booksManager.getAllAvailable();
                        availableBooks.forEach((i, book) -> {
                            String bookDetails = String.format(
                                    "%d- %s | %s | %d", i, book.getTitle(), book.getAuthor(), book.getYearPublished());
                            System.out.println(bookDetails);
                        });
                        break;
                    case "Checkout a book":
                        String bookReference;
                        User user = userAccountsManager.getLoggedUser();
                        System.out.println("Please, type the reference of the book:");
                        bookReference = scanner.nextLine();
                        booksManager.checkout(bookReference, user);
                        break;
                    case "Return a book":
                        userAccountsManager.getLoggedUser(); //checks if logged user
                        System.out.println("Please, type the reference of the book:");
                        bookReference = scanner.nextLine();
                        booksManager.returnElement(bookReference);
                        break;
                    case "Quit":
                        System.exit(0);
                        break;
                    case "List of movies":
                        HashMap<Integer, Movie> movies = (HashMap<Integer, Movie>) moviesManager.getAllAvailable();
                        movies.forEach((i, movie) -> {
                            String movieDetails = String.format(
                                    "%d- %s | %d | %s | %s",
                                    i, movie.getTitle(), movie.getYear(), movie.getDirector(),
                                    movie.getRating() == 0 ? "unrated" : movie.getRating());
                            System.out.println(movieDetails);
                        });
                        break;
                    case "Checkout a movie":
                        System.out.println("Please, type the reference of the movie:");
                        String movieReference = scanner.nextLine();
                        moviesManager.checkout(movieReference);
                        break;
                    case "View books checked out":
                        HashMap<Integer, Book> checkedOutBooks = (HashMap<Integer, Book>) booksManager.getAllCheckedOut();
                        checkedOutBooks.forEach((i, book) -> {
                            String bookDetails = String.format(
                                    "%d- %s | %s | %d [Checked out by: %s]",
                                    i, book.getTitle(), book.getAuthor(), book.getYearPublished(),
                                    book.getUserWhoCheckedOut());
                            System.out.println(bookDetails);
                        });
                        break;
                    case "Login":
                        System.out.println("Library number:");
                        String libraryNumber = scanner.nextLine();
                        System.out.println("Password:");
                        String password = scanner.nextLine();
                        userAccountsManager.logIn(libraryNumber,password);
                        break;
                    case "View my information":
                        User loggedUser = userAccountsManager.getLoggedUser();
                        System.out.println(String.format("Name: %s\nEmail: %s\nPhone: %s",
                                loggedUser.getName(), loggedUser.getEmail(), loggedUser.getPhoneNumber()));
                        break;
                }
                System.out.println("------------------");
                showMenu();
                manageOptionSelectedByTheUser();
            }
        }
    }

    private boolean isValidOptionInput(String option) {
        int optionNumber;
        try {
            optionNumber = Integer.valueOf(option);
        }
        catch (NumberFormatException e) {
            System.out.println(ERROR_INVALID_OPTION);
            return false;
        }
        if (optionNumber <= 0 || optionNumber > options.size()) {
            System.out.println(ERROR_INVALID_OPTION);
            return false;
        }
        return true;
    }

}
