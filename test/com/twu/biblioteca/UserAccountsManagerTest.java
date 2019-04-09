package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

}
