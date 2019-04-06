package com.twu.biblioteca;

public class WelcomeMessage {

    public static void sayHello() {
        System.out.println(getWelcomeMessage());
    }

    public static String getWelcomeMessage() {
        return "Welcome to Biblioteca. Your one-stop-shop for good book titles in Banglore";
    }
}
