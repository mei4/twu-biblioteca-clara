package com.twu.biblioteca;

import com.twu.biblioteca.catalogElement.book.Book;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BookTest {

    Book greatGatsby = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925);


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
        assertEquals(greatGatsby.getTitle(), "The Great Gatsby");
        assertEquals(greatGatsby.getAuthor(), "F. Scott Fitzgerald");
        assertEquals(greatGatsby.getYearPublished(), 1925);

        // mock creation
        List mockedList = mock(List.class);

// using mock object - it does not throw any "unexpected interaction" exception
        mockedList.add("one");
        mockedList.clear();

        // selective, explicit, highly readable verification
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }


}
