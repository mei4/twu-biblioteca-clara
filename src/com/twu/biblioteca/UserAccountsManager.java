package com.twu.biblioteca;

import java.util.List;

public class UserAccountsManager {

    public List<User> users;
    UserSession userSession = new UserSession();

    public UserAccountsManager(List<User> users) {
        this.users = users;
    }

    public boolean areValidCredentials(String libraryNumber, String password) {
        User userAccount = getUserByLibraryNumber(libraryNumber);
        return userAccount.isValidPassword(password);
    }

    public void logIn(String libraryNumber, String password) {
        User userAccount = getUserByLibraryNumber(libraryNumber);
        if (areValidCredentials(libraryNumber, password)) {
            userSession.logIn(userAccount);
            System.out.println("Successful login");
        }
        else System.out.println("Wrong credentials");
    }

    public boolean isLoggedUser () {
        return userSession.isLoggedIn();
    }

    private User getUserByLibraryNumber(String libraryNumber) {
        return users.stream().filter(user -> user.getLibraryNumber().equals(libraryNumber)).findFirst().get();
    }

    public User getLoggedUser() {
        return userSession.getUser();
    }

    public void showLoggedUserInformation() {
        System.out.println(userSession.getUserInformation());
    }
}
