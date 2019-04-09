package com.twu.biblioteca;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserTest {

    User user = new User("ABC-1234", "nicePassword");

    @Test
    public void checkThatThePasswordIsValidWithCorrectLoginCredentials () {
        assertTrue(user.isValidPassword("nicePassword"));
    }

    @Test
    public void checkThatThePasswordIsInvalidWithIncorrectLoginCredentials () {
        assertFalse(user.isValidPassword("superExtraHyperSuperLongPassword"));
    }
}
