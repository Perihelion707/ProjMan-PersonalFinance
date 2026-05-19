package edu.neumont.mgt101.view;

import java.util.Scanner;

public class TUI {
    private static Scanner scanner = null;

    public TUI() {
        scanner = new Scanner(System.in);
    }

    public static void println(String text) {
        System.out.println(text);
    }

    public static String inputString() {
        return scanner.nextLine();
    }

    public static void printSeparator() {
        System.out.println("----------------------------------------");
    }


    public static void printIncomeAndExpenses() {
        System.out.println("Displaying Income and Expenses...");
    }

    public static int inputMenuOption(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid whole number.");
            }
        }
    }
}
