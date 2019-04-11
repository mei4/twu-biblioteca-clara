package com.twu.biblioteca.catalogElement.movie;

import com.twu.biblioteca.catalogElement.movie.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoviesManagerTest {

    public List<Movie> movies = new ArrayList<>(Arrays.asList(
            new Movie("Totoro", 1988, "Hayao Miyazaki", 10),
            new Movie("The Lion King", 1994, "Rob Minkoff and Roger Allers", 8),
            new Movie("Captain Marvel", 2019, "Anna Boden and Ryan Fleck", 0)));

//    @Test
//    public void checkThatAllTheMoviesAndTheirDirectorYearAndRatingAreDisplayed() {
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(out));
//
//        MoviesManager moviesManager = new MoviesManager(movies);
//        moviesManager.showAll();
//
//        assertEquals("1- Totoro | 1988 | Hayao Miyazaki | 10\n" +
//                "2- The Lion King | 1994 | Rob Minkoff and Roger Allers | 8\n" +
//                "3- Captain Marvel | 2019 | Anna Boden and Ryan Fleck | unrated\n", out.toString());
//    }

//    @Test
//    public void checkThatABookCanBeCheckedOut() {
//        MoviesManager moviesManager = new MoviesManager(movies);
//        moviesManager.checkout("1", null);
//        assertTrue(movies.get(0).isCheckout());
//    }
}
