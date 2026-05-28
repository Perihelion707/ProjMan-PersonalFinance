package edu.neumont.mgt101.view;

import java.util.Scanner;

public class TUI {
    private final static Scanner scanner = new Scanner(System.in);

    public TUI() {

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
                if (output > min || output < max) {
                    return output;
                } else {
                    System.out.println("That value isn't allowed. Please pick a number between " + min + "-" + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid whole number.");
            }
        }
    }

    public static boolean yesOrNoHandler(String prompt){
        String yesOrNo;
        boolean incorrectInput = true;
        do {
            yesOrNo = Console.getStringInput(prompt + "\n(Yes or No)", false);
            if (yesOrNo.equalsIgnoreCase("Yes") || yesOrNo.equalsIgnoreCase("No")) {
                incorrectInput = false;
            }else{
                Console.writeLn("You must enter Yes or No!", Console.TextColor.RED);
            }
        }while(incorrectInput);
        if(yesOrNo.equalsIgnoreCase("Yes")){
            return true;
        } else{
            return false;
        }
    }
}
