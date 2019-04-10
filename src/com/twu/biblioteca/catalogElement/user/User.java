package com.twu.biblioteca.catalogElement.user;

public class User {

    private String libraryNumber;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;

    public User(String libraryNumber, String password, String name, String email, String phoneNumber) {
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public boolean isValidPassword(String password) {
        return password.equals(this.password);
    }

    public String getLibraryNumber() {
        return  libraryNumber;
    }

    public String getInformation() {
        return name + "\n" + email + "\n" + phoneNumber;
    }
}
