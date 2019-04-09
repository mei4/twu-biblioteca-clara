package com.twu.biblioteca;

public class User {

    String libraryNumber;
    String password;

    public User(String libraryNumber, String password) {
        this.libraryNumber = libraryNumber;
        this.password = password;
    }

    public boolean isValidPassword(String password) {
        return password.equals(this.password);
    }

    public String getLibraryNumber() {
        return  libraryNumber;
    }
}
