package com.twu.biblioteca.user;

import com.twu.biblioteca.user.User;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserTest {

    User user = new User("ABC-1234", "nicePassword",
            "Joe", "joe@biblioteca.com", "612345678");

    @Test
    public void checkThatThePasswordIsValidWithCorrectLoginCredentials () {
        assertTrue(user.isValidPassword("nicePassword"));
    }

    @Test
    public void checkThatThePasswordIsInvalidWithIncorrectLoginCredentials () {
        assertFalse(user.isValidPassword("superExtraHyperSuperLongPassword"));
    }

    @Test
    public void checkThatAUserHasNameEmailAndPhoneNumber() {
        assertEquals(user.getName(), "Joe");
        assertEquals(user.getEmail(), "joe@biblioteca.com");
        assertEquals(user.getPhoneNumber(), "612345678");
    }

//    @Test
//    public void checkThatUserInformationIncludeNameEmailAndPhoneNumber() {
//        String userInformation = user.getInformation();
//        assertEquals(userInformation,
//                "Joe\n" +
//                "joe@biblioteca.com\n" +
//                "612345678");
//    }
}
