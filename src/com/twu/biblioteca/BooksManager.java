package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BooksManager {

    public List<String> books = new ArrayList<>(Arrays.asList("To Kill a Mockingbird", "Pride and Prejudice",
            "The Diary of Anne Frank", "1984", "Harry Potter and the Sorcerer's Stone",
            "The Lord of the Rings", "The Great Gatsby"));

    public void showAllBooks() {
        System.out.println(books);
    }
}
