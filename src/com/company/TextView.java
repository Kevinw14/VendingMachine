package com.company;

import java.util.Scanner;
import java.util.InputMismatchException;

public class TextView {

    private final Scanner stringScanner;
    private final Scanner numberScanner;

    public TextView() {
        stringScanner = new Scanner(System.in);
        numberScanner = new Scanner(System.in);
    }

    public String prompt(String msg) throws InputMismatchException {
        System.out.println(msg);
        return stringScanner.nextLine();
    }

    public int promptForInt(String msg) throws InputMismatchException {
        System.out.print(msg);
        return numberScanner.nextInt();
    }

    public int promptForInt() throws InputMismatchException {
        return numberScanner.nextInt();
    }

    public double promptForDouble() throws InputMismatchException {
        return numberScanner.nextDouble();
    }

    public void display(Object msg) {
        System.out.println(msg);
    }

    public void display(String format, Object... args) {
        System.out.printf("\n" + format, args);
    }

}
