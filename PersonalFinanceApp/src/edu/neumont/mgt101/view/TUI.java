package edu.neumont.mgt101.view;

import edu.neumont.mgt101.models.Transaction;
import java.util.List;

public class TUI {

    public static void printHeader(String title) {
        Console.writeLn("\n========================================", Console.TextColor.CYAN);
        Console.writeLn("   " + title.toUpperCase(), Console.TextColor.WHITE);
        Console.writeLn("========================================", Console.TextColor.CYAN);
    }

    public static void displayTransactions(List<Transaction> transactions) {
        if (transactions == null || transactions.isEmpty()) {
            Console.writeLn("No transactions logged yet.", Console.TextColor.YELLOW);
            return;
        }

        printHeader("Transaction History");

        Console.write(" ID   ", Console.TextColor.PURPLE);
        System.out.println("| TYPE     | NAME                 | AMOUNT       ");
        Console.writeLn("---------------------------------------------------------", Console.TextColor.CYAN);

        int id = 1;
        for (Transaction t : transactions) {
            Console.TextColor moneyColor = t.getMoneyAmount() >= 0 ? Console.TextColor.GREEN : Console.TextColor.RED;

            System.out.printf(" %-4d | %-8s | %-20s | ", id++, t.getTransactionType(), t.getTransactionName());
            Console.writeLn(String.format("$%-11.2f", t.getMoneyAmount()), moneyColor);
        }
        Console.writeLn("---------------------------------------------------------", Console.TextColor.CYAN);
    }

    public static void displayDashboard(String name, double balance) {
        Console.writeLn("\n+---------------------------------------+", Console.TextColor.BLUE);
        System.out.printf("  USER: %-15s ", name);
        Console.write("NET WORTH: ", Console.TextColor.WHITE);
        Console.writeLn(String.format("$%.2f", balance), balance >= 0 ? Console.TextColor.GREEN : Console.TextColor.RED);
        Console.writeLn("+---------------------------------------+", Console.TextColor.BLUE);
    }

    public static int promptMenuSelection(String title, String[] options) {
        printHeader(title);
        for (int i = 0; i < options.length; i++) {
            Console.write("  " + (i + 1) + ".", Console.TextColor.YELLOW);
            System.out.println(" " + options[i]);
        }
        System.out.println();
        return Console.getIntInput("Select an option number:", 1, options.length);
    }

    public static String readString(String prompt) {
        return Console.getStringInput(prompt);
    }

    public static int readInt(String prompt) {
        return Console.getIntInput(prompt);
    }

    public static int readIntRange(String prompt, int min, int max) {
        return Console.getIntInput(prompt, min, max);
    }

    public static double readDouble(String prompt) {
        return Console.getDoubleInput(prompt);
    }

    public static boolean readBoolean(String prompt, String positive, String negative) {
        return Console.getBooleanInput(prompt, positive, negative);
    }

    public static void showWarning(String message) {
        Console.writeLn(message, Console.TextColor.YELLOW);
    }

    public static void showError(String message) {
        Console.writeLn(message, Console.TextColor.RED);
    }

    public static void showSuccess(String message) {
        Console.writeLn(message, Console.TextColor.GREEN);
    }

    public static void showMessage(String message) {
        Console.writeLn(message, Console.TextColor.WHITE);
    }
}
