package com.twu.biblioteca;

import java.util.ArrayList;

public class BibliotecaApp {

    public static void main(String[] args) {

        WelcomeMessage.sayHello();
        new OptionsMenu(new ArrayList<>()).showMenu();
    }
}
