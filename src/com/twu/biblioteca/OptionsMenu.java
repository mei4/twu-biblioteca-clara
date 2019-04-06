package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.IntStream;

public class OptionsMenu {

    public static final String ERROR_INVALID_OPTION = "Please select a valid option";
    private List<String> options;

    public OptionsMenu(List<String> options) {
        this.options = options;
    }

    public void showMenu() {
        IntStream.range(0, options.size()).forEach(i -> System.out.println(i + 1 + "- " + options.get(i)));
    }

    public void getOptionSelectedByTheUser() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String option = reader.readLine();
        isValidInput(option);
    }

    private boolean isValidInput(String option) {
        int optionNumber = 0;
        try {
            optionNumber = Integer.valueOf(option);
        }
        catch (NumberFormatException e) {
            System.out.println(ERROR_INVALID_OPTION);
            return false;
        }
        if (optionNumber <= 0 || optionNumber > options.size()) {
            System.out.println(ERROR_INVALID_OPTION);
            return false;
        }
        return true;
    }

}
