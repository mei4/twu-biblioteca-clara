package com.twu.biblioteca.user;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserAccountsManagerTest {

    List<User> users = new ArrayList<>(Arrays.asList(
            new User("ABC-1234", "nicePassword", "Abece", "abc@biblioteca.com", "9876543210"),
            new User("XYZ-4321", "superNicePassword", null, null, null)));
    UserAccountsManager userAccountsManager = new UserAccountsManager(users);

//    @Test
//    public void checkIfGivenCorrectNumberAndPasswordIsValidUser() {
//        assertTrue(userAccountsManager.areValidCredentials("ABC-1234", "nicePassword"));
//    }
//
//    @Test
//    public void checkIfGivenNumberAndWrongPasswordIsValidUser() {
//        assertFalse(userAccountsManager.areValidCredentials("XYZ-4321", "nicePassword"));
//    }

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

//    @Test
//    public void checkThatTheInformationOfAUserIsDisplayed() {
//        userAccountsManager.logIn("ABC-1234", "nicePassword");
//
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(out));
//
//        userAccountsManager.showLoggedUserInformation();
//
//        assertEquals("Abece\n" +
//                "abc@biblioteca.com\n" +
//                "9876543210\n", out.toString());
//    }

    @Test
    public void checkThatLoggedUserIsReturned() {
        userAccountsManager.logIn("ABC-1234", "nicePassword");

        assertEquals(users.get(0), userAccountsManager.getLoggedUser());
    }
}
