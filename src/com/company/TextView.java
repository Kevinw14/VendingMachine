package com.company;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * TextView class provides methods to display output to the console
 * and take input in different forms
 *
 * @author Kevin Wood
 * @version 1.0
 */
public class TextView {

    private final Scanner stringScanner;
    private final Scanner numberScanner;

    public TextView() {
        stringScanner = new Scanner(System.in);
        numberScanner = new Scanner(System.in);
    }

    /**
     * Prompts the user with a message and waits for input as a string
     * @param msg The message to be prompted to the user
     * @return The string the user provides as input
     */
    public String prompt(String msg) {
        System.out.println(msg);
        return stringScanner.nextLine();
    }

    /**
     * Prompts the user with a message and waits for input as an int
     *
     * @param msg The message to be prompted to the user
     * @return The int the user provides
     * @throws InputMismatchException Exception is thrown when user enters anything but an int
     */
    public int promptForInt(String msg) throws InputMismatchException {
        System.out.print(msg);
        return numberScanner.nextInt();
    }

    /**
     * Prompts the user with a message and waits for input as a double
     *
     * @param msg The message to be prompted to the user
     * @return The double the user provides
     * @throws InputMismatchException Exception is thrown when user enters anything but an double
     */
    public double promptForDouble(String msg) throws InputMismatchException {
        System.out.println(msg);
        return numberScanner.nextDouble();
    }

    /**
     * Used to fix a bug that causes an infinite loop when exception is thrown
     */
    public void clearNumberScanner() {
        numberScanner.nextLine();
    }

    /**
     * Display a message to the user
     *
     * @param msg The message to be prompted to the user
     */
    public void display(Object msg) {
        System.out.println(msg);
    }

    /**
     * Takes in a formatted string and the arguments associated with the placeholders
     *
     * @param format String format to be displayed
     * @param args arguments to be formatted into the string
     */
    public void display(String format, Object... args) {
        System.out.printf("\n" + format, args);
    }

}
