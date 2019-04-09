package com.twu.biblioteca;

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
                        booksManager.showAll();
                        break;
                    case "Checkout a book":
                        System.out.println("Please, type the reference of the book:");
                        String bookReference = scanner.nextLine();
                        booksManager.checkout(bookReference);
                        break;
                    case "Return a book":
                        System.out.println("Please, type the reference of the book:");
                        bookReference = scanner.nextLine();
                        booksManager.returnElement(bookReference);
                        break;
                    case "Quit":
                        System.exit(0);
                        break;
                    case "List of movies":
                        moviesManager.showAll();
                        break;
                    case "Checkout a movie":
                        System.out.println("Please, type the reference of the movie:");
                        String movieReference = scanner.nextLine();
                        moviesManager.checkout(movieReference);
                        break;
                    case "View books checked out":
                        booksManager.showAllCheckedOut();
                        break;
                    case "Login":
                        System.out.println("Library number:");
                        String libraryNumber = scanner.nextLine();
                        System.out.println("Password:");
                        String password = scanner.nextLine();
                        userAccountsManager.areValidCredentials(libraryNumber,password);
                        userAccountsManager.logIn(libraryNumber,password);
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
