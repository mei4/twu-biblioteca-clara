package com.twu.biblioteca.catalogElement;

import com.twu.biblioteca.catalogElement.CannotCheckOutCatalogElement;
import com.twu.biblioteca.catalogElement.CannotReturnCatalogElement;
import com.twu.biblioteca.catalogElement.CatalogElement;
import com.twu.biblioteca.catalogElement.book.Book;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CatalogElementTest {

    CatalogElement catalogElement = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925);

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();


    @Test
    public void checkThatACatalogElementCanBeCheckedOut() {
        assertFalse(catalogElement.isCheckout());
        catalogElement.checkout(null);
        assertTrue(catalogElement.isCheckout());
    }

    @Test
    public void checkConfirmationMessageWhenCatalogElementCheckedOut() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        catalogElement.checkout("Abc");

        assertEquals("Thank you! Enjoy the book\n", out.toString());
    }

    @Test
    public void checkedOutCatalogElementCanNotBeCheckedOutAgain() {
        catalogElement.checkout(null);
//        exceptionRule.expect(CannotCheckOutCatalogElement.class);
//        exceptionRule.expectMessage("Sorry, that book is not available");
        try {
            catalogElement.checkout("Abc");
        } catch (CannotCheckOutCatalogElement e) {
            assertEquals(e.getMessage(), "Sorry, that book is not available");
            assertNull(catalogElement.getLibraryNumberCheckout());
        }
    }

    @Test
    public void checkUsernameWhoCheckoutIsStored () {
        String libraryNumber = "Abc";
        catalogElement.checkout(libraryNumber);
        assertEquals(catalogElement.getLibraryNumberCheckout(), libraryNumber);
    }

    @Test
    public void checkThatACatalogElementCanBeReturned() {
        catalogElement.checkout("Abc");
        assertTrue(catalogElement.isCheckout());
        catalogElement.returnCatalogElement ();
        assertFalse(catalogElement.isCheckout());
    }

    @Test
    public void checkConfirmationMessageWhenCatalogElementReturned() {
        catalogElement.checkout("Abc");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        catalogElement.returnCatalogElement ();

        assertEquals("Thank you for returning the book\n", out.toString());
    }

    @Test
    public void checkedOutCatalogElementCanNotBeReturnedIfItsAlreadyAvailable() {
        exceptionRule.expect(CannotReturnCatalogElement.class);
        catalogElement.returnCatalogElement();
    }

    @Test
    public void checkMessageThrownWhenCheckOutCatalogElementWhichIsAlreadyAvailable() {
        exceptionRule.expectMessage("That is not a valid book to return");
        catalogElement.returnCatalogElement();
    }

    @Test
    public void noUsernameWhenCatalogElementReturned() {
        catalogElement.checkout("Abc");
        catalogElement.returnCatalogElement();
        assertNull(catalogElement.getLibraryNumberCheckout());
    }
}
