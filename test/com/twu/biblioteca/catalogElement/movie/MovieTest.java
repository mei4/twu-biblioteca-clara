package com.twu.biblioteca.catalogElement.movie;

import org.junit.Test;

import static org.junit.Assert.*;

public class MovieTest {

    Movie totoro = new Movie("Totoro", 1988, "Hayao Miyazaki", 10);

    @Test
    public void checkThatAMovieHasATitleYearAndDirector() {
        assertEquals(totoro.getTitle(), "Totoro");
        assertEquals(totoro.getYear(), 1988);
        assertEquals(totoro.getDirector(), "Hayao Miyazaki");
        assertEquals(totoro.getRating(), 10);
    }

    @Test
    public void checkThatACatalogElementCanBeCheckedOut() {
        assertFalse(totoro.isCheckout());
        totoro.checkout();
        assertTrue(totoro.isCheckout());
    }
}
