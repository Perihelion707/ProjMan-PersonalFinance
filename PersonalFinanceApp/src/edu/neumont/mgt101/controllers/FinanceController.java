package edu.neumont.mgt101.controllers;

import edu.neumont.mgt101.models.FinancialGoal;
import edu.neumont.mgt101.models.Transaction;
import edu.neumont.mgt101.models.TransactionType;
import edu.neumont.mgt101.models.User;
import edu.neumont.mgt101.view.Console;
import edu.neumont.mgt101.view.TUI;

public class FinanceController
{
    private final LoginManager loginManager = new LoginManager();
    private User currUser;

    public void run()
    {
        while (true) {
            int userChoice = Console.getIntInput("What would you like to do? 1. Login 2. Create Account 3. Exit App");
            switch (userChoice) // Need to make it so it only goes to main menu after login.
            {
                case 1 -> login();
                case 2 -> createUserAccount();
            }
            if (userChoice == 3) {break;}
            while (true) {
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
                    default -> mainMenu();
                }

                if (userChoice == 8)
                {
                    break;
                }
            }
        }
    }
    public void login()
    {
        while (true)
        {
            String username = Console.getStringInput("Enter Username: ");
            String password = Console.getStringInput("Enter Password: ");
            currUser = loginManager.login(username, password);
            if (currUser == null) {
                TUI.println("Username or password is incorrect.");
            }
            else
            {
                TUI.println("Logged in successfully. \n");
                break;
            }
        }

    }
    public int mainMenu(){
        //Print user info
        TUI.println("\n1. Add Transaction " +
                "\n2. View Transaction History " +
                "\n3. Add Goal " +
                "\n4. View Goals " +
                "\n5. Remove Goal " +
                "\n6. Complete Goal" +
                "\n7. Remove Transaction " +
                "\n8. Save and Exit ");
        return TUI.inputMenuOption("What would you like to do?",1,8);
    }
    /**
     * Asks the User for Financial Goal Parameters and then adds that goal to the user
     */
    public void addGoal()
    {
        while (true)
        {
            try {
                TUI.println("What is the max amount of money you are allowing yourself to spend during this goal");
                String goalMoney = TUI.inputString();
                int goalAmount = Integer.parseInt(goalMoney);
                TUI.println("What do you want this goal to be named");
                String goalName = TUI.inputString();
                TUI.println("How many days should this goal last? \n");
                String goalLength = TUI.inputString();
                int goalDays = Integer.parseInt(goalLength);

                FinancialGoal userGoal = new FinancialGoal(goalAmount, goalName, goalDays, false);
                currUser.financialGoals.add(userGoal);
                break;

            }
            catch (Exception e)
            {
                TUI.println(e.getMessage());
            }
        }
    }
    public void changeIncome(User currUser, double income, String transactionName){
        currUser.setMoney(income);
        Transaction newTransaction;
        boolean wouldLikeDescription = TUI.yesOrNoHandler("Would you like to add a description to your transaction?");
        if(wouldLikeDescription){
            TUI.println("Enter the description for your transaction.");
            String description = TUI.inputString();
            newTransaction = new Transaction(TransactionType.DEPOSIT, transactionName, description, income);
        } else {
            newTransaction = new Transaction(TransactionType.DEPOSIT, transactionName, income);
        }
        currUser.transactions.add(newTransaction);
    }
    public void changeExpenses(){}
    /**
     * Asks the user for a transaction type, name, description, and money amount. Allows user to not include description if they don't want it.
     */
    public void addTransaction()
    {
        while(true)
        {
            try
            {
                TUI.println("What Type is this Transaction? (D/W)");
                String transactionString = TUI.inputString();
                TransactionType transactionType;
                if (transactionString.equalsIgnoreCase("D"))
                {
                    transactionType = TransactionType.DEPOSIT;
                }
                else if (transactionString.equalsIgnoreCase("W"))
                {
                    transactionType = TransactionType.WITHDRAWAL;
                }
                else
                {
                    TUI.println("Invalid Transaction");
                    continue;
                }
                TUI.println("What is the transaction name?");
                String transactionName = TUI.inputString();
                TUI.println("What was the amount of money involved with the transaction?");
                String moneyString = TUI.inputString();
                double money = Double.parseDouble(moneyString);
                //TUI.println("Would you like to add a description to the this transaction?");
                //String description = TUI.inputString();
                boolean wantsDescription = TUI.yesOrNoHandler("Would you like to add a description to the this transaction?");
                //description.equalsIgnoreCase("Y")
                if (wantsDescription)
                {
                    //TUI.println("What is the description?");
                    String description = Console.getStringInput("What is the description?", false);
                    //String description = TUI.inputString();
                    Transaction transaction = new Transaction(transactionType, transactionName, description, money);
                    currUser.transactions.add(transaction);
                    TUI.println("Transaction stored");
                    currUser.setMoney(currUser.getMoney() + money);
                    break;
                }
                else
                {
                    Transaction transaction = new Transaction(transactionType, transactionName, money);
                    currUser.transactions.add(transaction);
                    TUI.println("Transaction stored");
                    currUser.setMoney(currUser.getMoney() + money);
                    break;
                }
            }
            catch (Exception NumberFormatException)
            {
                TUI.println("Invalid input type");
            }
        }
    }
    public void saveAndLogout()
    {
        FileManager.writeUserData(currUser);
        loginManager.logout();
    }
    public void completeGoal(){
        if(currUser.financialGoals.isEmpty()){
            TUI.println("You have no goals set");
        }else{
            int i = 1;
            for(FinancialGoal goal : currUser.financialGoals){
                TUI.println(i + ". " + goal.toString());
                i++;
            }
            int goalSelect = Console.getIntInput("Which goal would you like to complete?", 1, i);
            if(currUser.financialGoals.get(goalSelect-1).getIsComplete()){
                TUI.println("You have already completed this goal");
            }else {
                currUser.financialGoals.get(goalSelect - 1).setIsComplete(true);
                TUI.println("Your goal has been marked as complete");
            }
        }
    }


    /**
     * Asks the user for a int input which goal they would like to remove after printing all current user goals. Then it removes goal based on the corresponding number.
     */
    public void removeGoal()
    {
        while(true) {
            try {
                TUI.println("Which goal would you like to remove? (Just type the number)");
                for (int i = 1; i <= currUser.financialGoals.size(); i++) {
                    TUI.println(i + ". " + currUser.financialGoals.get(i).getGoalName());
                }
                int choice = Console.getIntInput("\n");
                currUser.financialGoals.remove(choice);
                break;
            }
            catch (Exception e) {
                TUI.println("Invalid goal number");
            }
        }
    }

    /**
     * Should only be used if the user added a transaction by accident or messed it up
     * Asks the user for a int input which transaction they would like to remove after printing all current user transaction. Then it removes transaction based on the corresponding number. It also undoes the money change to the user.
     */
    public void removeTransaction()
    {
        while(true) {
            try {
                TUI.println("Which transaction would you like to remove? (Just type the number)");
                for (int i = 1; i <= currUser.transactions.size(); i++) {
                    TUI.println(i + ". " + currUser.transactions.get(i).getTransactionName());
                }
                int choice = Console.getIntInput("\n");
                currUser.setMoney(currUser.getMoney() - currUser.transactions.get(choice).getMoneyAmount());
                currUser.transactions.remove(choice);
                break;
            }
            catch (Exception e) {
                TUI.println("Invalid goal number");
            }
        }
    }

    public void viewTransactionHistory()
    {
        if(currUser.transactions.isEmpty()){
            TUI.println("You have done no transactions");
        }else {
            int i = 1;
            for (Transaction transaction : currUser.transactions) {
                TUI.println(i + ". " + transaction.toString());
                i++;
            }
        }
    }

    public void viewGoals()
    {
        if(currUser.financialGoals.isEmpty()){
            TUI.println("You have no goals set");
        }else {
            int i = 1;
            TUI.println("Completed Goals");
            for (FinancialGoal goal : currUser.financialGoals) {
                if(goal.getIsComplete()) {
                    TUI.println(i + ". " + goal.toString());
                    i++;
                }
            }
            TUI.println("Incomplete Goals");
            for (FinancialGoal goal : currUser.financialGoals) {
                if(!goal.getIsComplete()) {
                    TUI.println(i + ". " + goal.toString());
                    i++;
                }
            }
        }
    }

    public void createUserAccount()
    {
        String username = Console.getStringInput("Enter your username: ");
        String password = Console.getStringInput("Enter your password: ");
        String name = Console.getStringInput("Enter your Full Name: ");
        double money = Console.getDoubleInput("Enter your Net Worth: ");
        loginManager.createAccount(name, username, password, money);
    }
}
