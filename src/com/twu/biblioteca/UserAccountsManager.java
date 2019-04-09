package com.twu.biblioteca;

import java.util.List;

public class UserAccountsManager {

    public List<User> users;

    public UserAccountsManager(List<User> users) {
        this.users = users;
    }

    public boolean areValidCredentials(String libraryNumber, String password) {
        User userAccount = users.stream().filter(user -> user.getLibraryNumber() == libraryNumber)
                .findFirst().get();
        return userAccount.isValidPassword(password);
    }
}
