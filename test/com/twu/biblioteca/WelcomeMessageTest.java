package com.twu.biblioteca;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class WelcomeMessageTest {

    private String welcomeMessage = "Welcome to Biblioteca. Your one-stop-shop for good book titles in Banglore";

//    @Test
//    public void shouldReturnTheWelcomeMessage() {
//        String message = WelcomeMessage.getWelcomeMessage();
//        assertEquals(message, welcomeMessage);
//    }

    @Test
    public void shouldWriteTheWelcomeMessage() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        WelcomeMessage.sayHello();

        assertEquals(welcomeMessage + "\n", out.toString());

    }
}
