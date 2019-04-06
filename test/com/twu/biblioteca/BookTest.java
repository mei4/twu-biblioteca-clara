package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookTest {

    @Test
    public void checkThatBookDetailsIncludeAuthorAndYearPublished() {
        Book greatGatsby = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925);
        String greatGatsbyDetails = greatGatsby.getDetails();
        assertEquals(greatGatsbyDetails, "The Great Gatsby | F. Scott Fitzgerald | 1925");
    }
}
