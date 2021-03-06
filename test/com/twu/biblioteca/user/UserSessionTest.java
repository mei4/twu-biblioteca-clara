package com.twu.biblioteca.user;

import com.twu.biblioteca.user.User;
import com.twu.biblioteca.user.UserSession;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserSessionTest {

    User user = new User("ABC-1234", "nicePassword", null, null, null);

    UserSession userSession = new UserSession();

    @Test
    public void checkThatIfNothingHappensThereIsNoLoggedUser() {
        assertFalse(userSession.isLoggedIn());
    }

    @Test
    public void checkThatThereIsALoggedUserAfterLogIn() {
        userSession.logIn(user);
        assertTrue(userSession.isLoggedIn());
    }


}
