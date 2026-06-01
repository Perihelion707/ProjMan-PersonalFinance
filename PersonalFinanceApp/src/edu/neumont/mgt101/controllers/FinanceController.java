package edu.neumont.mgt101.controllers;

import edu.neumont.mgt101.models.FinancialGoal;
import edu.neumont.mgt101.models.Transaction;
import edu.neumont.mgt101.models.TransactionType;
import edu.neumont.mgt101.models.User;
import edu.neumont.mgt101.view.TUI;

public class FinanceController {
    private final LoginManager loginManager = new LoginManager();
    private User currUser;

    public void run() {
        while (true) {
            String[] startOptions = {"Login", "Create Account", "Exit App"};
            int userChoice = TUI.promptMenuSelection("Welcome Screen", startOptions);

            if (userChoice == 1) {
                login();
            } else if (userChoice == 2) {
                createUserAccount();
                continue;
            } else if (userChoice == 3) {
                break;
            }

            do {
                userChoice = mainMenu();
                switch (userChoice) {
                    case 1 -> addTransaction();
                    case 2 -> viewTransactionHistory();
                    case 3 -> addGoal();
                    case 4 -> viewGoals();
                    case 5 -> removeGoal();
                    case 6 -> completeGoal();
                    case 7 -> removeTransaction();
                    case 8 -> saveAndLogout();
                }
            } while (userChoice != 8);
        }
    }

    public void login() {
        while (true) {
            String username = TUI.readString("Enter Username: ");
            String password = TUI.readString("Enter Password: ");
            currUser = loginManager.login(username, password);

            if (currUser == null) {
                TUI.showError("Username or password is incorrect.");
            } else {
                TUI.showSuccess("Logged in successfully.\n");
                break;
            }
        }
    }

    public int mainMenu() {
        TUI.displayDashboard(currUser.getName(), currUser.getMoney());

        String[] menuOptions = {
                "Add Transaction", "View Transaction History", "Add Goal",
                "View Goals", "Remove Goal", "Complete Goal",
                "Remove Transaction", "Save and Logout"
        };
        return TUI.promptMenuSelection("Main Menu", menuOptions);
    }

    public void addGoal() {
        int goalAmount = TUI.readInt("What is the max amount of money you are allowing yourself to spend during this goal?");
        String goalName = TUI.readString("What do you want this goal to be named?");
        int goalDays = TUI.readInt("How many days should this goal last?");

        FinancialGoal userGoal = new FinancialGoal(goalAmount, goalName, goalDays, false);
        currUser.financialGoals.add(userGoal);
        TUI.showSuccess("Goal added successfully!");
    }

    public void addTransaction() {
        TransactionType transactionType = null;

        while (transactionType == null) {
            String transactionString = TUI.readString("What Type is this Transaction? (D/W)").toUpperCase();
            if (transactionString.equals("D")) {
                transactionType = TransactionType.DEPOSIT;
            } else if (transactionString.equals("W")) {
                transactionType = TransactionType.WITHDRAWAL;
            } else {
                TUI.showError("Invalid Transaction Type. Choose D or W.");
            }
        }

        String transactionName = TUI.readString("What is the transaction name?");
        double money = TUI.readDouble("What was the amount of money involved with the transaction?");

        boolean wantsDescription = TUI.readBoolean("Would you like to add a description to this transaction?", "Yes", "No");
        Transaction transaction;

        if (wantsDescription) {
            String description = TUI.readString("What is the description?");
            transaction = new Transaction(transactionType, transactionName, description, money);
        } else {
            transaction = new Transaction(transactionType, transactionName, money);
        }

        currUser.transactions.add(transaction);
        currUser.setMoney(currUser.getMoney() + money);
        TUI.showSuccess("Transaction stored successfully.");
    }

    public void completeGoal() {
        if (currUser.financialGoals.isEmpty()) {
            TUI.showWarning("You have no goals set.");
            return;
        }

        for (int i = 0; i < currUser.financialGoals.size(); i++) {
            TUI.showMessage((i + 1) + ". " + currUser.financialGoals.get(i));
        }

        int goalSelect = TUI.readIntRange("Which goal would you like to complete?", 1, currUser.financialGoals.size());
        FinancialGoal selectedGoal = currUser.financialGoals.get(goalSelect - 1);

        if (selectedGoal.getIsComplete()) {
            TUI.showWarning("You have already completed this goal.");
        } else {
            selectedGoal.setIsComplete(true);
            TUI.showSuccess("Your goal has been marked as complete!");
        }
    }

    public void removeGoal() {
        if (currUser.financialGoals.isEmpty()) {
            TUI.showWarning("You have no goals set.");
            return;
        }

        TUI.showMessage("Which goal would you like to remove? (Just type the number)");
        for (int i = 0; i < currUser.financialGoals.size(); i++) {
            TUI.showMessage((i + 1) + ". " + currUser.financialGoals.get(i).getGoalName());
        }

        int choice = TUI.readIntRange("Enter number:", 1, currUser.financialGoals.size());
        currUser.financialGoals.remove(choice - 1);
        TUI.showSuccess("Goal successfully removed.");
    }

    public void removeTransaction() {
        if (currUser.transactions.isEmpty()) {
            TUI.showWarning("You have no transactions to remove.");
            return;
        }

        TUI.showMessage("Which transaction would you like to remove? (Just type the number)");
        for (int i = 0; i < currUser.transactions.size(); i++) {
            TUI.showMessage((i + 1) + ". " + currUser.transactions.get(i).getTransactionName());
        }

        int choice = TUI.readIntRange("Enter number:", 1, currUser.transactions.size());
        int actualIndex = choice - 1;

        currUser.setMoney(currUser.getMoney() - currUser.transactions.get(actualIndex).getMoneyAmount());
        currUser.transactions.remove(actualIndex);
        TUI.showSuccess("Transaction successfully removed.");
    }

    public void viewTransactionHistory() {
        TUI.displayTransactions(currUser.transactions);
    }

    public void viewGoals() {
        if (currUser.financialGoals.isEmpty()) {
            TUI.showWarning("You have no goals set.");
            return;
        }

        TUI.printHeader("Completed Goals");
        int i = 1;
        for (FinancialGoal goal : currUser.financialGoals) {
            if (goal.getIsComplete()) {
                TUI.showSuccess(i + ". " + goal);
                i++;
            }
        }

        TUI.printHeader("Incomplete Goals");
        for (FinancialGoal goal : currUser.financialGoals) {
            if (!goal.getIsComplete()) {
                TUI.showWarning(i + ". " + goal);
                i++;
            }
        }
    }

    public void createUserAccount() {
        String username = TUI.readString("Enter your username: ");
        String password = TUI.readString("Enter your password: ");
        String name = TUI.readString("Enter your Full Name: ");
        double money = TUI.readDouble("Enter your Net Worth: ");
        loginManager.createAccount(name, username, password, money);
        TUI.showSuccess("Account created successfully!");
    }

    public void saveAndLogout() {
        FileManager.writeUserData(currUser);
        loginManager.logout();
        TUI.showMessage("Logged out safely.");
    }
}