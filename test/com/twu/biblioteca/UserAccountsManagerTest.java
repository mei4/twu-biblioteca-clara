package com.twu.biblioteca;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class UserAccountsManagerTest {

    List<User> users = new ArrayList<>(Arrays.asList(
            new User("ABC-1234", "nicePassword"),
            new User("XYZ-4321", "superNicePassword")));
    UserAccountsManager userAccountsManager = new UserAccountsManager(users);

    @Test
    public void checkIfGivenCorrectNumberAndPasswordIsValidUser() {
        assertTrue(userAccountsManager.areValidCredentials("ABC-1234", "nicePassword"));
    }

    @Test
    public void checkIfGivenNumberAndWrongPasswordIsValidUser() {
        assertFalse(userAccountsManager.areValidCredentials("XYZ-4321", "nicePassword"));
    }

    @Test
    public void checkThatAMessageIsThrownWhenAUserLogsInWithWrongCredentials() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        userAccountsManager.logIn("ABC-1234", "wrongPassword");

        assertEquals("Wrong credentials\n", out.toString());
    }

    @Test
    public void checkThatAMessageIsThrownWhenAUserLogsInWithCorrectCredentials() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        userAccountsManager.logIn("ABC-1234", "nicePassword");

        assertEquals("Successful login\n", out.toString());
    }
}
