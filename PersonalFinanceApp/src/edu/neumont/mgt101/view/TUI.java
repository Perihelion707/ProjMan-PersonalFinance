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

    public static int inputMenuOption(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int output = Integer.parseInt(input);
                if (output < min || output > max) {
                    return output;
                } else {
                    System.out.println("That value isn't allowed. Please pick a number between " + min + "-" + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid whole number.");
            }
        }
    }
}
