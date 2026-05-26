package edu.neumont.mgt101.controllers;

import edu.neumont.mgt101.models.FinancialGoal;
import edu.neumont.mgt101.models.Transaction;
import edu.neumont.mgt101.models.TransactionType;
import edu.neumont.mgt101.models.User;
import edu.neumont.mgt101.view.TUI;

public class FinanceController
{
    private final LoginManager loginManager = new LoginManager();
    private User currUser;

    public void run(){}
    public void login(){}
    public int mainMenu(){
        //Print user info
        TUI.println("1. Add Transaction " +
                "\n2. View Transaction History " +
                "\n3. Add Goal " +
                "\n4. View Goals " +
                "\n5. Exit ");
        return TUI.inputMenuOption("What would you like to do?",1,5);
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
                float money = Float.parseFloat(moneyString);
                TUI.println("Would you like to add a description to the this transaction?");
                String description = TUI.inputString();
                if (description.equalsIgnoreCase("Y"))
                {
                    TUI.println("What is the description?");
                    description = TUI.inputString();
                    Transaction transaction = new Transaction(transactionType, transactionName, description, money);
                    currUser.transactions.add(transaction);
                    TUI.println("Transaction stored");
                    break;
                }
                else
                {
                    Transaction transaction = new Transaction(transactionType, transactionName, money);
                    currUser.transactions.add(transaction);
                    TUI.println("Transaction stored");
                    break;
                }
            }
            catch (Exception e)
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
}
