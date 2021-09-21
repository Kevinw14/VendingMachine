package com.company;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 *
 */
public class TextView {

    private final Scanner stringScanner;
    private final Scanner numberScanner;

    public TextView() {
        stringScanner = new Scanner(System.in);
        numberScanner = new Scanner(System.in);
    }

    /**
     *
     * @param msg
     * @return
     */
    public String prompt(String msg) {
        System.out.println(msg);
        return stringScanner.nextLine();
    }

    /**
     *
     * @param msg
     * @return
     * @throws InputMismatchException
     */
    public int promptForInt(String msg) throws InputMismatchException {
        System.out.print(msg);
        return numberScanner.nextInt();
    }

    /**
     *
     * @param msg
     * @return
     * @throws InputMismatchException
     */
    public double promptForDouble(String msg) throws InputMismatchException {
        System.out.println(msg);
        return numberScanner.nextDouble();
    }

    public void clearNumberScanner() {
        numberScanner.nextLine();
    }
    /**
     *
     * @param msg
     */
    public void display(Object msg) {
        System.out.println(msg);
    }

    /**
     *
     * @param format
     * @param args
     */
    public void display(String format, Object... args) {
        System.out.printf("\n" + format, args);
    }

}
