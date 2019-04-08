package com.twu.biblioteca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BibliotecaApp {

    public static void main(String[] args) throws IOException {

        WelcomeMessage.sayHello();
        List<Book> books = new ArrayList<>(Arrays.asList(
                new Book("To Kill a Mockingbird", "Harper Lee", 1988),
                new Book("Pride and Prejudice", "Jane Austen", 1813),
                new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925)));
        BooksManager booksManager = new BooksManager(books);
        List<String> options = new ArrayList<>(Arrays.asList(
                "List of Books",
                "Checkout a book",
                "Return a book",
                "Quit"));
        OptionsMenu optionsMenu = new OptionsMenu(options, booksManager);

        optionsMenu.showMenu();
        optionsMenu.manageOptionSelectedByTheUser();

    }
}
