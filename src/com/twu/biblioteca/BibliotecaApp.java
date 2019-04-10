package com.twu.biblioteca;

import com.twu.biblioteca.catalogElement.book.Book;
import com.twu.biblioteca.catalogElement.book.BooksManager;
import com.twu.biblioteca.catalogElement.movie.Movie;
import com.twu.biblioteca.catalogElement.movie.MoviesManager;
import com.twu.biblioteca.catalogElement.user.User;
import com.twu.biblioteca.catalogElement.user.UserAccountsManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BibliotecaApp {

    public static void main(String[] args) {

        WelcomeMessage.sayHello();

        List<Book> books = new ArrayList<>(Arrays.asList(
                new Book("To Kill a Mockingbird", "Harper Lee", 1988),
                new Book("Pride and Prejudice", "Jane Austen", 1813),
                new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925)));
        BooksManager booksManager = new BooksManager(books);

        List<Movie> movies = new ArrayList<>(Arrays.asList(
                new Movie("Totoro", 1988, "Hayao Miyazaki", 10),
                new Movie("The Lion King", 1994, "Rob Minkoff and Roger Allers", 8),
                new Movie("Captain Marvel", 2019, "Anna Boden and Ryan Fleck", 0)));
        MoviesManager moviesManager = new MoviesManager(movies);

        List<String> options = new ArrayList<>(Arrays.asList(
                "List of books",
                "Checkout a book",
                "Return a book",
                "List of movies",
                "Checkout a movie",
                "View books checked out",
                "Login",
                "View my information",
                "Quit"));

        List<User> users = new ArrayList<>(Arrays.asList(
                new User("ABC-1234", "nicePassword",
                        "Abece", "abc@biblioteca.com", "9876543210"),
                new User("XYZ-4321", "superNicePassword",
                        "Xyz", "xyz@biblioteca.com", "99999999")));
        UserAccountsManager userAccountsManager = new UserAccountsManager(users);

        OptionsMenu optionsMenu = new OptionsMenu(options, booksManager, moviesManager, userAccountsManager);

        optionsMenu.showMenu();
        optionsMenu.manageOptionSelectedByTheUser();

    }
}
