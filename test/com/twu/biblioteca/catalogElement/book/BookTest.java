package com.twu.biblioteca.catalogElement.book;

import com.twu.biblioteca.catalogElement.book.Book;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BookTest {



//    @Test
//    public void checkThatBookDetailsIncludeAuthorAndYearPublished() {
//        String greatGatsbyDetails = greatGatsby.getDetails();
//        assertEquals(greatGatsbyDetails, "The Great Gatsby | F. Scott Fitzgerald | 1925");
//    }
//
//    @Test
//    public void checkThatABookCanBeCheckedOut() {
//        assertFalse(greatGatsby.isCheckout());
//        greatGatsby.setCheckout(true, null);
//        assertTrue(greatGatsby.isCheckout());
//    }
//
//    @Test
//    public void checkThatABookCanBeReturned() {
//        greatGatsby.setCheckout(true, null);
//        assertTrue(greatGatsby.isCheckout());
//        greatGatsby.setCheckout(false, null);
//        assertFalse(greatGatsby.isCheckout());
//    }

    @Test
    public void checkThatABookHasATitleAuthorAndYearPublished() {
        Book greatGatsby = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925);
        assertEquals(greatGatsby.getTitle(), "The Great Gatsby");
        assertEquals(greatGatsby.getAuthor(), "F. Scott Fitzgerald");
        assertEquals(greatGatsby.getYearPublished(), 1925);
        assertNull(greatGatsby.getLibraryNumberCheckout());
    }


}
