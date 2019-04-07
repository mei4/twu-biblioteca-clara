package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.IntStream;

public class OptionsMenu {

    public static final String ERROR_INVALID_OPTION = "Please select a valid option";
    private List<String> options;
    private BooksManager booksManager;

    public OptionsMenu(List<String> options, BooksManager booksManager) {
        this.options = options;
        this.booksManager = booksManager;
    }

    public void showMenu() {
        IntStream.range(0, options.size()).forEach(i -> System.out.println(i + 1 + "- " + options.get(i)));
    }

    public void manageOptionSelectedByTheUser() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String optionIndex = reader.readLine();
        if (isValidOptionInput(optionIndex)) {
            String optionName = options.get(Integer.valueOf(optionIndex) - 1);
            switch (optionName) {
                case "List of books":
                    booksManager.showAllBooks();
                    break;
                case "Checkout a book":
                    System.out.println("Please, type the reference of the book:");
                    String bookReference = reader.readLine();
                    booksManager.checkoutBook(bookReference);
                    break;
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
