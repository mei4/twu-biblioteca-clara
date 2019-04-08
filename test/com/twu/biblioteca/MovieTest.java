package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MovieTest {

    Movie totoro = new Movie("Totoro", 1988, "Hayao Miyazaki", 10);

    @Test
    public void checkThatMovieDetailsIncludeNameYearDirectorAndRating() {
        String totoroDetails = totoro.getDetails();
        assertEquals(totoroDetails, "Totoro | 1988 | Hayao Miyazaki | 10");
    }
}