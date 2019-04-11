package com.twu.biblioteca.catalogElement.movie;

import com.twu.biblioteca.catalogElement.movie.Movie;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MovieTest {

    Movie totoro = new Movie("Totoro", 1988, "Hayao Miyazaki", 10);

//    @Test
//    public void checkThatMovieDetailsIncludeNameYearDirectorAndRating() {
//        String totoroDetails = totoro.getDetails();
//        assertEquals(totoroDetails, "Totoro | 1988 | Hayao Miyazaki | 10");
//    }
//
//    @Test
//    public void checkThatMovieCanBeCheckedOut() {
//        assertFalse(totoro.isCheckout());
//        totoro.setCheckout(true, null);
//        assertTrue(totoro.isCheckout());
//    }

    @Test
    public void checkThatAMovieHasATitleYearAndDirector() {
        assertEquals(totoro.getTitle(), "Totoro");
        assertEquals(totoro.getYear(), 1988);
        assertEquals(totoro.getDirector(), "Hayao Miyazaki");
        assertEquals(totoro.getRating(), 10);
    }
}
