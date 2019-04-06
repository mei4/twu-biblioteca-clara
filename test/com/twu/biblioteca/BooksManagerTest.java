package com.twu.biblioteca;


import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BooksManagerTest {

    public List<String> books = new ArrayList<>(Arrays.asList("To Kill a Mockingbird", "Pride and Prejudice",
            "The Diary of Anne Frank", "1984", "Harry Potter and the Sorcerer's Stone",
            "The Lord of the Rings", "The Great Gatsby"));

    @Test
    public void checkThatAllTheBooksAreDisplayed() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        BooksManager booksManager = new BooksManager();
        booksManager.showAllBooks();

        assertEquals(books + "\n", out.toString());

    }

}
