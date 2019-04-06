package com.twu.biblioteca;


import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BooksManagerTest {

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

        assertEquals("To Kill a Mockingbird | Harper Lee | 1988\n" +
                "Pride and Prejudice | Jane Austen | 1813\n" +
                "The Great Gatsby | F. Scott Fitzgerald | 1925\n", out.toString());

    }
}
