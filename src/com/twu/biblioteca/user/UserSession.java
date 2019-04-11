package com.twu.biblioteca.user;

public class UserSession {

    private boolean isLoggedIn;
    private User user;

    public UserSession() {
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void logIn(User user) {
        this.isLoggedIn = true;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    //TODO: delete
    public String getUserInformation() {
        return user.getInformation();
    }
}
