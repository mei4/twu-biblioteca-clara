package com.twu.biblioteca;

import java.util.List;

public class BooksManager {

    public List<Book> books;

    public BooksManager(List<Book> books) {
        this.books = books;
    }

    public void showAllBooks() {
        books.forEach(book -> System.out.println(book.getDetails()));
    }
}
