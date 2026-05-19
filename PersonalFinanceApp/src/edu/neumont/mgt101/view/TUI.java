package edu.neumont.mgt101.view;

import java.util.Scanner;

public class TUI {
    private final Scanner scanner;

    public TUI() {
        this.scanner = new Scanner(System.in);
    }

    public void println(String text) {
        System.out.println(text);
    }

    public String inputString() {
        return scanner.nextLine();
    }

    public void printSeperator() {
        System.out.println("----------------------------------------");
    }


    public void printIncomeAndExpenses() {
        System.out.println("Displaying Income and Expenses...");
    }

    public int inputMenuOption(String prompt) {
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
