package com.twu.biblioteca.catalogElement.book;

import com.twu.biblioteca.catalogElement.exceptions.CannotCheckOutCatalogElement;
import com.twu.biblioteca.catalogElement.exceptions.CannotReturnCatalogElement;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class BookTest {

    Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925);

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void checkThatABookHasATitleAuthorAndYearPublished() {
        Book greatGatsby = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925);
        assertEquals(greatGatsby.getTitle(), "The Great Gatsby");
        assertEquals(greatGatsby.getAuthor(), "F. Scott Fitzgerald");
        assertEquals(greatGatsby.getYearPublished(), 1925);
        assertNull(greatGatsby.getLibraryNumberCheckout());
    }

    @Test
    public void checkThatACatalogElementCanBeCheckedOut() {
        assertFalse(book.isCheckout());
        book.checkout(null);
        assertTrue(book.isCheckout());
    }

    @Test
    public void checkConfirmationMessageWhenCatalogElementCheckedOut() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        book.checkout("Abc");

        assertEquals("Thank you! Enjoy the book\n", out.toString());
    }

    @Test
    public void checkedOutBookCanNotBeCheckedOutAgain() {
        book.checkout(null);
        try {
            book.checkout("Abc");
        } catch (CannotCheckOutCatalogElement e) {
            assertEquals(e.getMessage(), "Sorry, that book is not available");
            assertNull(book.getLibraryNumberCheckout());
        }
    }

    @Test
    public void checkUsernameWhoCheckoutIsStored () {
        String libraryNumber = "Abc";
        book.checkout(libraryNumber);
        assertEquals(book.getLibraryNumberCheckout(), libraryNumber);
    }

    @Test
    public void checkThatABookCanBeReturned() {
        book.checkout("Abc");
        assertTrue(book.isCheckout());
        book.returnCatalogElement ();
        assertFalse(book.isCheckout());
    }

    @Test
    public void checkConfirmationMessageWhenBookReturned() {
        book.checkout("Abc");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        book.returnCatalogElement ();

        assertEquals("Thank you for returning the book\n", out.toString());
    }

    @Test
    public void checkedOutBookCanNotBeReturnedIfItsAlreadyAvailable() {
        exceptionRule.expect(CannotReturnCatalogElement.class);
        book.returnCatalogElement();
    }

    @Test
    public void checkMessageThrownWhenCheckOutBookWhichIsAlreadyAvailable() {
        exceptionRule.expectMessage("That is not a valid book to return");
        book.returnCatalogElement();
    }

    @Test
    public void noUsernameWhenBookReturned() {
        book.checkout("Abc");
        book.returnCatalogElement();
        assertNull(book.getLibraryNumberCheckout());
    }
}
