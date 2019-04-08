package com.twu.biblioteca;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MoviesManagerTest {

    public List<Movie> movies = new ArrayList<>(Arrays.asList(
            new Movie("Totoro", 1988, "Hayao Miyazaki", 10),
            new Movie("The Lion King", 1994, "Rob Minkoff and Roger Allers", 8),
            new Movie("Captain Marvel", 2019, "Anna Boden and Ryan Fleck", 0)));

    @Test
    public void checkThatAllTheMoviesAndTheirDirectorYearAndRatingAreDisplayed() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        MoviesManager moviesManager = new MoviesManager(movies);
        moviesManager.showAllMovies();

        assertEquals("1- Totoro | 1988 | Hayao Miyazaki | 10\n" +
                "2- The Lion King | 1994 | Rob Minkoff and Roger Allers | 8\n" +
                "3- Captain Marvel | 2019 | Anna Boden and Ryan Fleck | unrated\n", out.toString());
    }
}