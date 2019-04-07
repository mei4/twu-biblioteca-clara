package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {

    Book greatGatsby = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925);

    @Test
    public void checkThatBookDetailsIncludeAuthorAndYearPublished() {
        String greatGatsbyDetails = greatGatsby.getDetails();
        assertEquals(greatGatsbyDetails, "The Great Gatsby | F. Scott Fitzgerald | 1925");
    }

    @Test
    public void checkThatABookCanBeCheckedOut() {
        assertFalse(greatGatsby.isCheckedOut());
        greatGatsby.setCheckedOut();
        assertTrue(greatGatsby.isCheckedOut());
    }
}