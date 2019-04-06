package com.twu.biblioteca;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class OptionsMenuTest {

    String option1 = "List of books";
    String errorInvalidOption = "Please select a valid option";

    @Test
    public void checkThatNothingIsShowedWhenEmptyMenu() {
        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>());

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        optionsMenu.showMenu();

        assertEquals( "", out.toString());
    }

    @Test
    public void checkThatTheMenuIsPrintedWhenOneOption() {
        List<String> options = new ArrayList<>(Arrays.asList(option1));
        OptionsMenu optionsMenu = new OptionsMenu(options);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        optionsMenu.showMenu();

        assertEquals("1- " + option1 + "\n", out.toString());
    }

    @Test
    public void checkThatAnErrorMessageIsThrownWhenInvalidOptionSelected() throws IOException {
        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(Arrays.asList(option1)));
        System.setIn(new ByteArrayInputStream("2".getBytes()));
        optionsMenu.showMenu();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        optionsMenu.getOptionSelectedByTheUser();

        assertEquals(errorInvalidOption + "\n", out.toString());
    }

    @Test
    public void checkThatAnErrorMessageIsNotThrownWhenValidOptionSelected() throws IOException {
        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(Arrays.asList(option1)));
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        optionsMenu.showMenu();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        optionsMenu.getOptionSelectedByTheUser();

        assertNotEquals(errorInvalidOption + "\n", out.toString());
    }

    @Test
    public void checkThatAnErrorMessageIsThrownWhenNumberFormatException() throws IOException {
        OptionsMenu optionsMenu = new OptionsMenu(new ArrayList<>(Arrays.asList(option1)));
        System.setIn(new ByteArrayInputStream("List of fiction books".getBytes()));
        optionsMenu.showMenu();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        optionsMenu.getOptionSelectedByTheUser();

        assertEquals(errorInvalidOption + "\n", out.toString());
    }
}
