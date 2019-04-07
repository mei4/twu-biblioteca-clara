package com.twu.biblioteca;

import java.util.List;
import java.util.stream.IntStream;

public class BooksManager {

    public List<Book> books;

    public BooksManager(List<Book> books) {
        this.books = books;
    }

    public void showAllBooks() {
        IntStream.range(0, books.size()).forEach(i -> System.out.println(i + 1 + "- " + books.get(i).getDetails()));;
    }

    public void checkoutBook(String bookReference) {
        Book book = books.get(Integer.valueOf(bookReference) - 1);
        book.setCheckedOut();
    }
}
